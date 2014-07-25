/**
 * 
 */
package com.rest.service.poc;

/**
 * @author Somnath.Upadhyay
 *
 */
public enum ServiceResolver {

	ADDRESS("addressService"),
	NAME("nameService");
	
	private String serviceName;
	
	private ServiceResolver(String serviceName){
		this.serviceName = serviceName;
	}
	
	public String getServiceName(){
		return this.serviceName;
	}
}
