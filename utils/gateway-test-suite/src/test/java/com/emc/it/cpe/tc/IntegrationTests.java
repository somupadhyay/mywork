/**
 * 
 */
package com.emc.it.cpe.tc;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author upadhs5
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber" })
public class IntegrationTests{
	

}
