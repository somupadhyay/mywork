/**
 * 
 */
package org.sb.jersey.poc.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

/**
 * @author Somnath.Upadhyay
 *
 */
@Component
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        return Response.serverError().entity(exception.getMessage()).build();
    }
}