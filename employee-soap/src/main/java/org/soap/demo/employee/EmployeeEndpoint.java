/**
 * 
 */
package org.soap.demo.employee;

import java.util.Random;

import javax.xml.bind.JAXBElement;

import org.soap.demo.employee.domain.EmployeeRequestType;
import org.soap.demo.employee.domain.EmployeeResponseType;
import org.soap.demo.employee.domain.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import lombok.extern.slf4j.Slf4j;

/**
 * @author upadhs5
 *
 */
@Endpoint
@Slf4j
public class EmployeeEndpoint {

	private static final String NAMESPACE_URI = "http://www.soap.org/demo/employee/domain";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "EmployeeRequest")
	@ResponsePayload
	public JAXBElement<EmployeeResponseType> saveEmployee(@RequestPayload EmployeeRequestType request) {
		log.info("Received Request {}", request);
		Random rand = new Random();
		long val = rand.nextLong();
		EmployeeResponseType response = new EmployeeResponseType();
		response.setActive(request.isActive());
		response.setAge(request.getAge());
		response.setFirstName(request.getFirstName());
		response.setLastName(request.getLastName());
		response.setId(val + "");
		ObjectFactory factory = new ObjectFactory();
		return factory.createEmployeeResponse(response);
	}
}
