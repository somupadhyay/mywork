/**
 * 
 */
package com.emc.it.cpe.tc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.emc.it.cpe.common.utils.EncodeNDecode;
/*import com.emc.it.cpe.tc.config.CucumberStepsDefinition;*/
import com.emc.it.cpe.utils.CPEHeaderInterceptor;
import com.emc.it.cpe.utils.JsonUtils;
import com.emc.it.cpe.utils.RestTemplateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author upadhs5
 *
 */
/*@CucumberStepsDefinition*/
public class OAuth2Test {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Test.class);

	private static final String TOKEN_KEY = "access_token";

	private MultiValueMap<String, String> map;
	
	private RestTemplate restTemplate;

	private ResponseEntity<String> response;

	/**
	 *    The oauth2 token https://ssgosgdev.isus.emc.com/auth/oauth/v2/token and the json:
	 * 
	 * @param name
	 * @throws JsonProcessingException 
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	@Given("^The oauth2 token (.+) and the payload:$")
	public void the_oauth2_token_and_the_payload(String tokenUrl, String payload) throws JsonProcessingException, IOException{
		LOGGER.info("Starting the oauth test.");
		boolean turnOffSSL = tokenUrl.contains("8443")?true:false;
		restTemplate = RestTemplateUtils.newRestTemplate(turnOffSSL);
		String token = getToken(tokenUrl, payload);
		Map<String, String> headers = new HashMap<>();
		restTemplate.getInterceptors().add(CPEHeaderInterceptor.Builder.fromHeaders(headers).oAuthToken(token).build());
	}

	private String getToken(String tokenUrl, String payload) throws JsonProcessingException, IOException{
		boolean turnOffSSL = tokenUrl.contains("8443")?true:false;
		RestTemplate _restTemplate = RestTemplateUtils.newRestTemplate(turnOffSSL);
		
		Map<String, String> _map = JsonUtils.getStringMapOfJsonString(payload);
		HttpHeaders headers = new HttpHeaders();
		map = new LinkedMultiValueMap<>();
		_map.forEach((key,value) ->{
			if(key.equalsIgnoreCase("password")){
				value = EncodeNDecode.decode(value);
			}
			map.put(key, Arrays.asList(value));
		});
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = _restTemplate.postForEntity(tokenUrl, request , String.class);
		LOGGER.info("OAuth Response: "+response);
		Assert.assertTrue("Failed to generate oauth token service returned <"+response.getStatusCode()+"> status code, expected is 200", response.getStatusCode().value()==200);
		return JsonUtils.getStringMapOfJsonString(response.getBody()).get(TOKEN_KEY);
	}

	/**
	 * When I access the https://ssgosgdev.isus.emc.com/dev/ldapsearch/api/persons url with queryParam ntid=upadhs5
	 * @param ntid
	 */
	@When("^I access the (.+) url with queryParam (.+)$")
	public void i_access_the_url_with_queryParam(String url, String queryParam) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.query(queryParam);
		response = this.restTemplate.getForEntity(builder.build().encode().toUriString(), String.class);
	}

	/**
	 * I get 200 status code and response payload:
	 * @param status
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	@Then("^I get (\\d+) status code and response payload:$")
	public void i_get_status_code_and_response_payload(int status, String payload) throws JsonProcessingException, IOException {
		JsonNode expected = JsonUtils.getObjectMapper().readTree(payload);
		JsonNode actual = JsonUtils.getObjectMapper().readTree(response.getBody()); 
		Assert.assertEquals(expected, actual);
		Assert.assertEquals("Expected the http status to be same. ", status, this.response.getStatusCode().value());

	}
}
