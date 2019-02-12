/**
 * 
 */
package org.gateway.test.suite.tc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.gateway.test.suite.common.utils.EncodeNDecode;
import org.gateway.test.suite.utils.CPEHeaderInterceptor;
import org.gateway.test.suite.utils.JsonUtils;
import org.gateway.test.suite.utils.RestTemplateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author somnath.upadhyay
 *
 */
public class ServiceOperationsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceOperationsTest.class);

	private RestTemplate restTemplate;

	private ResponseEntity<String> response;

	private String url;
	
	private static Map<String, String> mem = new HashMap<String, String>();

	
	@Given("^The layer7 rest api (.+) and credentials (.+) and (.+)$")
	public void the_layer7_rest_api_and_credentials_and(String url, String username, String password) {
		LOGGER.info("Setting up the given scenario.");
		boolean turnOffSSL = url.contains("8443")?true:false;
		this.restTemplate = RestTemplateUtils.newRestTemplate(turnOffSSL);
		this.url = url;
		restTemplate.getInterceptors()
				.add(CPEHeaderInterceptor.Builder
						.fromBasicAuth(EncodeNDecode.decode(username), EncodeNDecode.decode(password))
						.contentType(MediaType.APPLICATION_JSON_VALUE).build());
	}

	@When("^I (.+) perform http POST operation with the json payload:$")
	public void i_perform_http_post_operation_with_the_json_payload(String email, String payload) {
		LOGGER.info("Performing posting the received payload");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("email", email);
		response = restTemplate.postForEntity(builder.build().encode().toUriString(), payload, String.class);
	}

	@Then("^I should get the response status (\\d+) and payload:$")
	public void i_should_get_the_response_status_and_payload(int status, String payload) throws JsonProcessingException, IOException {
		JsonNode expected = JsonUtils.getObjectMapper().readTree(payload);
		JsonNode actual = JsonUtils.getObjectMapper().readTree(response.getBody());
		JsonNode data = actual.get("data");
		String serviceid = data.get("id").asText();
		String name = data.get("name").asText();
		mem.put(name, serviceid);
		Assert.assertEquals("Expected service name to be equal.", expected.get("data").get("name").asText(), name);
		LOGGER.info("Response" + response.getBody());
		Assert.assertEquals("Expected the http status to be same. ", status, this.response.getStatusCode().value());
	}

	@When("^I (.+) request to (.+) the existing service (.+) on (.+) host$")
	public void i_request_to_the_service(String email, String operation, String serviceName, String host){
		LOGGER.info("Received request to {} {} on {}",operation,serviceName,host);
		Assert.assertTrue("Serivce with name: "+serviceName+" does not exists", mem.containsKey(serviceName));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.path("/").path(mem.get(serviceName));
		
		HttpMethod method = HttpMethod.PUT; 
		if(operation.equalsIgnoreCase("disable")){
			builder.path("/status").path("/false");
			method = HttpMethod.PUT;
		}else if(operation.equalsIgnoreCase("enable")){
			builder.path("/status").path("/true");
			method = HttpMethod.PUT;
		}else if(operation.equalsIgnoreCase("delete")){
			method = HttpMethod.DELETE;
		}else{
			LOGGER.info("The operation is not allowed.");
			Assert.fail();
		}
		builder.queryParam("email", email);
		builder.queryParam("host", host);
		RequestEntity<String> requestEntity = new RequestEntity<String>(method, builder.build().encode().toUri());
		response = restTemplate.exchange(requestEntity, String.class);
		
	}
	
	@Then("^I should get http status code as (\\d+) and content:$")
	public void i_should_get_http_status_code_as_and_content(int status, String payload){
		LOGGER.info("Response" + response.getBody());
		Assert.assertEquals("Expected the http status to be same. ", status, this.response.getStatusCode().value());
	}
	
}
