package com.jacobs.visual.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacobs.visual.dto.Document;
import com.jacobs.visual.dto.Filters;
import com.jacobs.visual.dto.Link;
import com.jacobs.visual.dto.Node;
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

			System.out.println("##=> Final object: " + object);

			// RESPONSE - PROCESAMIENTO
			String jsonResponseEnd = conversorObjectToJson(object);
			System.out.println("##==> jsonResponse Final: " + jsonResponse);

			Response responseEnd = getJsonAsResponse(jsonResponseEnd);
			System.out.println("##==> Response Final: " + responseEnd);

			// Crear nodos de conexion
			List<Node> nodes = getNodes(responseEnd.getDocuments());
			String jsonNodes = conversorObjectToJson(nodes);
			System.out.println("##==> jsonNodes: " + jsonNodes);

			// Crear links de relaciones entre nodos
			List<Link> links = getLinks(responseEnd.getDocuments());
			String jsonLinks = conversorObjectToJson(links);
			System.out.println("##==> jsonlinks size: " + links.size());
			System.out.println("##==> jsonlinks: " + jsonLinks);

			Iterator<Node> it = nodes.iterator();
			Node node = null;
			while (it.hasNext()) {
				node = it.next();
				boolean exist = false;
				for (Link link : links) {
					if (node.getKey().equals(link.getFrom()) || node.getKey().equals(link.getTo())) {
						exist = true;
					}
				}
				if (!exist) {
					it.remove();
				}

			}

			jsonNodes = conversorObjectToJson(nodes);
			System.out.println("##==> jsonNodesFinal: " + jsonNodes);

			return object;

		} catch (Exception e) {
			System.out.println("##=> Error: " + e.getMessage());
			System.out.println("##=> Error: " + e.getCause());
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Permite generar los nodos a visualizar
	 * 
	 * @param documents
	 * @return
	 */
	private List<Node> getNodes(List<Document> documents) {

		List<Node> nodes = new ArrayList<>();
		Node node = null;
		for (Document document : documents) {
			if (null != document.getFields().getFilename()) {
				node = new Node();
				node.setKey(document.getFields().getPONumber());
//			node.setText("");
				node.setText(document.getFields().getDocumentType() + ": " + document.getFields().getFilename());

				if (null != document.getFields().getPartNumber()
						&& "[SEG13102314-303]".equals(document.getFields().getPartNumber().toString())) {
					node.setColor("RED");
				} else {
					node.setColor("YELLOW");
				}

				nodes.add(node);
			}
		}
		return nodes;
	}

	/**
	 * Permite generar los valores de las relaciones entre nodos
	 * 
	 * @param documents
	 * @return
	 */
	private List<Link> getLinks(List<Document> documents) {

		Map<String, String> map = getMapRelationship(documents);

		System.out.println("Mapa. size: " + map.size());

		System.out.println("Mapa: " + map.toString());

		List<Link> links = new ArrayList<>();

		map.forEach((k, v) -> {
			String[] data = k.split("_");
			Link link = new Link();
			link.setFrom(data[0]);
			link.setTo(data[1]);
			link.setText(v);

			links.add(link);

		});

		return links;
	}

	/**
	 * Permite almacenar un mapa con los valores que cumplen el criterio de relacion
	 * 
	 * @param documents
	 * 
	 * @return
	 */
	private Map<String, String> getMapRelationship(List<Document> documents) {
		Map<String, String> map = new HashMap<>();

		String keyInitial = null;
		String keyFinal = null;
		List<Document> newList = new ArrayList<>();
		for (Document documentInitial : documents) {
			if (null != documentInitial.getFields().getPartNumber()
					&& documentInitial.getFields().getPartNumber().contains("SEG13102314-303")) {
				newList.add(documentInitial);
			}
		}

		System.out.println("Tamaño lista final: newList: "+ newList.size());
		int ii =1;
		
		for (Document documentInitial : newList) {
			for (Document documentFinal : documents) {
				//System.out.println(documentInitial.getFields().getFilename() +" ---- " + documentFinal.getFields().getFilename() );
				
				if(null != documentFinal.getFields().getFilename() && documentFinal.getFields().getFilename().equals("ADP - Form J096_Gold Sheet")) {
					System.out.println("procesar");
				}
				if (null != documentInitial.getFields().getFilename() && null != documentFinal.getFields().getFilename()
						&& (!documentInitial.getFields().getPONumber()
								.equals(documentFinal.getFields().getPONumber()))) {
					if (dataContainsValue(documentInitial.getFields().getPartNumber(),
							documentFinal.getFields().getPartNumber())) {
						keyInitial = documentInitial.getFields().getPONumber() + "_"
								+ documentFinal.getFields().getPONumber();
						keyFinal = documentFinal.getFields().getPONumber() + "_"
								+ documentInitial.getFields().getPONumber();
						if (!(map.containsKey(keyInitial) || map.containsKey(keyFinal))) {
//							map.put(keyInitial, documentFinal.getFields().getDocumentType() + ":"
//									+ documentFinal.getFields().getFilename());

//							map.put(keyInitial, documentFinal.getFields().getFilename());
							map.put(keyInitial, ""+ii++);

						}
					}
				}
			}
		}

		return map;
	}

	/**
	 * Permite validar si un valor de un texto esta contenido en otro texto
	 * 
	 * @param initialData Texto con valores a buscar
	 * @param finalData   Texto de valores a comparar
	 * 
	 * @return true/false
	 */
	private boolean dataContainsValue(String initialData, String finalData) {

		if (null != initialData && null != finalData) {
			initialData = initialData.trim();
			initialData = initialData.substring(1, initialData.length() - 1);
			
			finalData = finalData.trim();
			finalData = finalData.substring(1, finalData.length() - 1);
			
			List<String> list = Arrays.asList(initialData.split(","));

			for (String value : list) {
				if (finalData.contains(value)) {
					return true;
				}
			}
		}
		return false;
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
	 * 
	 * @param request
	 * @param response
	 */
	private void modifyPartNumber(Request request, Response response) {
		String data = response.getDocuments().get(0).getFields().getPartNumber().trim();
		data = data.substring(1, data.length() - 1);

		List<String> list = Arrays.asList(data.split(","));

		request.getFilters().removeIf(x -> "PartNumber".equals(x.getField()));

		list.forEach(value -> addFilter(request.getFilters(), "PartNumber", "OR", value));

	}

	/**
	 * Permite agregar un filtro PartNumber
	 * 
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
