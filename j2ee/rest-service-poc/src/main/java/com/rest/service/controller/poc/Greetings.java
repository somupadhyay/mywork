/**
 * 
 */
package com.rest.service.controller.poc;

/**
 * @author Somnath.Upadhyay
 *
 */
public class Greetings {

	private final long id;
	private final String comment;
	
	public Greetings(long id, String comment) {
		super();
		this.id = id;
		this.comment = comment;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	
	
	
	
}
