package com.jacobs.visual.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacobs.visual.dto.Filters;
import com.jacobs.visual.dto.Request;
import com.jacobs.visual.dto.Response;

@RestController
public class JacobsController {

	@Autowired
	protected RestTemplate restTemplate;

	// http://localhost:8585//postDocument
	// http://209.249.189.32:9005/swagger-ui.html#/search-controller/searchUsingPOST

	// http://localhost:1111/postDocument

	@RequestMapping(value = "/postDocument", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object postGanador(@RequestBody Request request) {

		try {

			// REQUEST - PROCESAMIENTO
			String jsonRequest = conversorObjectToJson(request);
			System.out.println("##==> jsonRequest: " + jsonRequest);

			Request newRequest = getJsonAsRequest(jsonRequest);
			System.out.println("##==> Request: " + newRequest);

			// CONSULTA SERVICIO DOCUMENTOS
			Object object = searchDocument(request);

			// RESPONSE - PROCESAMIENTO
			String jsonResponse = conversorObjectToJson(object);
			System.out.println("##==> jsonResponse: " + jsonResponse);

			Response response = getJsonAsResponse(jsonResponse);
			System.out.println("##==> Response: " + response);

			newRequest.getFilters().removeIf(x -> "PartNumber".equals(x.getField()));

			// Prueba de modificar filter PartNumber
			modifyPartNumber(newRequest, response);

			object = searchDocument(newRequest);
			
			System.out.println("##==> newRequest: " + newRequest);
			
			System.out.println("##==> Json New request: " + conversorObjectToJson(newRequest));
			
			System.out.println("##=> Final object: " +  object);
			
			return object;

		} catch (Exception e) {
			System.out.println("##=> Error: " + e.getMessage());
			System.out.println("##=> Error: " + e.getCause());
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Permite consultar el servicio Search Documents
	 * 
	 * @param request Objeto de peticion
	 * 
	 * @return Json de respuesta del servicio
	 */
	private Object searchDocument(Request request) {
		System.out.println("##==> Search Document: " + request);
		return restTemplate.postForObject("http://209.249.189.32:9005//solr/services/document/search", request,
				Object.class);
	}

	/**
	 * Permite convertir de Json a Objeto request
	 * 
	 * @param requestJson String json
	 * 
	 * @return Objeto request
	 */
	private Request getJsonAsRequest(String jsonRequest) {

		try {
			ObjectMapper mapper = new ObjectMapper();

			Request request = mapper.readValue(jsonRequest, Request.class);

			// System.out.println("##==> Request-Json: " +
			// mapper.writeValueAsString(request));

			return request;

		} catch (Exception e) {
			System.out.println("##=> Error: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Permite obtener el objeto response
	 * 
	 * @param request
	 * 
	 * @return repsonse
	 */
	private Response getJsonAsResponse(String jsonResponse) {

		try {
			ObjectMapper mapper = new ObjectMapper();

			Response response = mapper.readValue(jsonResponse, Response.class);

			// System.out.println("##==> Response-Object: " + response);

			return response;

		} catch (Exception e) {
			System.out.println("##=> Error: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Permite convertir de Objeto a Json
	 * 
	 * @param Object
	 * @return String
	 */
	private String conversorObjectToJson(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			String jsonString = mapper.writeValueAsString(object);

			// System.out.println("##==> ObjectAsString: " + jsonString);

			return jsonString;

		} catch (Exception e) {
			System.out.println("##=> Error: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Permite modificar el Filtro Partnumber
	 * @param request
	 * @param response
	 */
	private void modifyPartNumber(Request request, Response response) {
		String data = response.getDocuments().get(0).getFields().getPartNumber().trim();
		data = data.substring(1, data.length()-1);
		
		List<String> list = Arrays.asList(data.split(","));
		
		request.getFilters().removeIf(x -> "PartNumber".equals(x.getField()));
		
		list.forEach(value-> addFilter(request.getFilters(), "PartNumber", "OR", value));
		
	}
	
	/**
	 * Permite agregar un filtro PartNumber
	 * @param filters
	 * @param field
	 * @param operator
	 * @param value
	 */
	private void addFilter(List<Filters> filters, String field, String operator, String value) {
		Filters newFilter = new Filters();
		newFilter.setField(field);
		newFilter.setOperator(operator);
		newFilter.setValue(value);
		newFilter.setClauses(null);

		filters.add(newFilter);

	}
}
