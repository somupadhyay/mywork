/**
 * 
 */
package org.sb.jersey.poc.controller;

import java.net.URI;
import java.util.Collection;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.sb.jersey.poc.domain.Employee;
import org.sb.jersey.poc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Somnath.Upadhyay
 *
 */
@Component
@Path("/employees")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GET
    @Produces("application/json")
    public Collection<Employee> getAllEmployees() {
		log.info("Get all employees");
        return employeeService.getAllEmployees();
    }
    @GET
    @Produces("application/json")
    @Path("/{eid}")
    public Employee getEmployee(@PathParam("eid") String eid) {
    	log.info("Get one employee {}",eid);
        return employeeService.getEmployee(eid);
    }
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addEmployee(Employee employee) {
    	log.info("Add employees {}",employee);
        String eid = employeeService.addEmployee(employee);
        return Response.created(URI.create("/" + eid)).build();
    }
    
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{eid}")
    public Response updateEmployee(@PathParam("eid") String eid, Employee employee) {
    	log.info("Update employee id={}, value{}",eid,employee);
        employeeService.updateEmployee(eid, employee);
        return Response.noContent().build();
    }
    @DELETE
    @Path("/{eid}")
    public Response deleteEmployee(@PathParam("eid") String eid) {
    	log.info("Delete employee {}",eid);
        employeeService.deleteEmployee(eid);
        return Response.ok().build();
    }
}
