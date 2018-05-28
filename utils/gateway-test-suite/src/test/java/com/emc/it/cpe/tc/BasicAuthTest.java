/**
 * 
 */
package com.emc.it.cpe.tc;

import java.io.IOException;

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
public class BasicAuthTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthTest.class);

	private RestTemplate restTemplate;

	private String url;

	private ResponseEntity<String> response;

	/**
	 * Scenario: Individual Nodes are reachable
	 * 
	 *     Given The API <url> auth <username> and <password>
    When I query for ntid upadhs5
    Then I get <httpStatus> response code 
	 * 
	 * @param name
	 * @throws IOException
	 */
	@Given("^The app (.+) auth (.+) and (.+)$")
	public void the_app_auth_and(String url, String username, String password) throws IOException {
		LOGGER.info("Starting to test the Basic Auth ");
		boolean turnOffSSL = url.contains("8443")?true:false;
		restTemplate = RestTemplateUtils.newRestTemplate(turnOffSSL);
		restTemplate.getInterceptors().add(CPEHeaderInterceptor.Builder.fromBasicAuth(username, password).build());
		this.url = url;
	}

	@When("^I Search for ntid (.+)$")
	public void i_search_for_ntid(String ntid) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("ntid", ntid);
		response = this.restTemplate.getForEntity(builder.build().encode().toUriString(), String.class);
	}

	@Then("^I get the (\\d+) response code$")
	public void i_get_the_response_code(int status) {
		Assert.assertEquals("Expected the http status to be same. ", status, this.response.getStatusCode().value());

	}
}
