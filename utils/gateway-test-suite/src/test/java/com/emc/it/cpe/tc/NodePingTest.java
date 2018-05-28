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
import com.emc.it.cpe.utils.RestTemplateUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author upadhs5
 *
 */
/*@CucumberStepsDefinition*/
public class NodePingTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(NodePingTest.class);

	private RestTemplate restTemplate;

	private String node;

	private ResponseEntity<String> response;

	/**
	 * Scenario: Individual Nodes are reachable
	 * 
	 * @param name
	 * @throws IOException
	 */
	@Given("^Node (.+) and new restTemplate$")
	public void node_and_new_restTemplate(String url) throws IOException {
		LOGGER.info("Starting the Test case");
		boolean turnOffSSL = url.contains("8443")?true:false;
		restTemplate = RestTemplateUtils.newRestTemplate(turnOffSSL);
		this.node = url;
	}

	@When("^I navigate the path (.+)$")
	public void i_navigate_the_path(String path) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(node);
		builder.path(path);
		response = this.restTemplate.getForEntity(builder.build().encode().toUriString(), String.class);
	}

	@Then("^I get a (\\d+) response and contains (.+)$")
	public void i_get_a_response_and_contains(int status, String content) {
		Assert.assertEquals("Expected the http status to be same. ", status, this.response.getStatusCode().value());
		Assert.assertTrue("Expected the response contains <OK> but was " + response.getBody(),
				response.getBody().contains(content));
	}
}
