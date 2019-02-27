package dump;

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
public class JacobsControllerOLD {

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

			String[] partNumbers = response.getDocuments().get(0).getFields().getPartNumber().split(",");
			for (int i = 0; i < partNumbers.length; i++) {
				String value = partNumbers[i].replace("[", "");
				value = value.replace("]", "");
				//System.out.println("PartNumber[" + i + "]:  " + value);
				addFilter(newRequest.getFilters(), "PartNumber", "OR", value);
			}

			object = searchDocument(newRequest);
			
			System.out.println("##==> newRequest: " + newRequest);
			
			System.out.println("##==> Json New request: " + conversorObjectToJson(newRequest));
			
			System.out.println("##=> Final object: " +  object);
			
//			System.out.println("##==> Request start: " + request);
//
//			for (Filters filter : request.getFilters()) {
//				System.out.println("Filter: " + filter.toString());
//			}
//
//			String jsonInString = mapper.writeValueAsString(request);
//
//			System.out.println("##==> jsonInString: " + jsonInString);
//
//			Object obj = restTemplate.postForObject("http://209.249.189.32:9005//solr/services/document/search",
//					request, Object.class);
//
//			// Object obj = restTemplate.postForObject("http://localhost:1111/postDocument",
//			// request, Object.class);
//
//			System.out.println("##==> obj: " + obj.toString());
//
//			String jsonInStringPost = mapper.writeValueAsString(obj);
//
//			System.out.println("##==> jsonInStringPost: " + jsonInStringPost.toString());
//
//			Response resp = mapper.readValue(jsonInStringPost, Response.class);
//			System.out.println("Response: " + resp.toString());
//
//			request.getFilters().removeIf(x -> "PartNumber".equals(x.getField()));
//
//			String[] partNumbers = resp.getDocuments().get(0).getFields().getPartNumber().split(",");
//			for (int i = 0; i < partNumbers.length; i++) {
//				String value = partNumbers[i].replace("[", "");
//				value = value.replace("]", "");
//				System.out.println("PartNumber[" + i + "]:  " + value);
//				addFilter(request.getFilters(), "PartNumber", "AND", value);
//			}
//
//			System.out.println("##==> Request end: " + request);
//
//			for (Filters filter : request.getFilters()) {
//				System.out.println("Filter: " + filter.toString());
//			}
//
//			jsonInString = mapper.writeValueAsString(request);
//
//			System.out.println("##==> jsonInString end: " + jsonInString);
//
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

	private void addFilter(List<Filters> filters, String field, String operator, String value) {
		Filters newFilter = new Filters();
		newFilter.setField(field);
		newFilter.setOperator(operator);
		newFilter.setValue(value);
		newFilter.setClauses(null);

		filters.add(newFilter);

	}
}
