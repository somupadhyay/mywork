/**
 * 
 */
package com.spring.poc.spring.integration.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.integration.Message;

/**
 * @author Somnath.Upadhyay
 *
 */
public class TestServiceActivator {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	public void doTask(Message<String> message){
		System.out.println("Called at "+dateFormat.format(new Date()));
		
	}
}
