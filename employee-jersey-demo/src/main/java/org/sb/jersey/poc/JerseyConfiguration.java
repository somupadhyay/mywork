/**
 * 
 */
package org.sb.jersey.poc;

import org.glassfish.jersey.server.ResourceConfig;
import org.sb.jersey.poc.controller.EmployeeController;
import org.sb.jersey.poc.controller.GenericExceptionMapper;
import org.springframework.stereotype.Component;

/**
 * @author Somnath.Upadhyay
 *
 */
@Component
public class JerseyConfiguration extends ResourceConfig {
	public JerseyConfiguration() {
		register(EmployeeController.class);
		register(GenericExceptionMapper.class);
	}
}