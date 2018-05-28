/**
 * 
 */
package com.emc.it.cpe.tc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/*import com.emc.it.cpe.tc.config.CucumberStepsDefinition;*/
import com.emc.it.cpe.utils.CPEHeaderInterceptor;
import com.emc.it.cpe.utils.RestTemplateUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author upadhs5
 *
 */
/*@CucumberStepsDefinition*/
public class ApikeyAuthTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApikeyAuthTest.class);

	private RestTemplate restTemplate;

	private String url;

	private ResponseEntity<String> response;

	/**
	 *    Given The API <url> and <apikey>
    When I query for upadhs5
    Then I get <httpStatus> status code 
	 * 
	 * @param name
	 * @throws IOException
	 */
	@Given("^The API (.+) and (.+)$")
	public void the_api_and(String url, String apikey) throws IOException {
		LOGGER.info("Starting to test the Apikey Authentication.");
		boolean turnOffSSL = url.contains("8443")?true:false;
		restTemplate = RestTemplateUtils.newRestTemplate(turnOffSSL);
		Map<String, String> headers = new HashMap<>();
		headers.put("apikey", apikey);
		restTemplate.getInterceptors().add(CPEHeaderInterceptor.Builder.fromHeaders(headers).build());
		this.url = url;
	}

	@When("^I query for (.+)$")
	public void i_query_for(String ntid) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("ntid", ntid);
		response = this.restTemplate.getForEntity(builder.build().encode().toUriString(), String.class);
	}

	@Then("^I get (\\d+) status code$")
	public void i_get_status_code(int status) {
		Assert.assertEquals("Expected the http status to be same. ", status, this.response.getStatusCode().value());

	}
}
