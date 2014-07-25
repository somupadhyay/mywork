/**
 * 
 */
package com.rest.service.controller.poc;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Somnath.Upadhyay
 *
 */
@Controller
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greetings")
	public @ResponseBody Greetings greeting(@RequestParam(value="name", required=false, defaultValue="World")String name){
		return new Greetings(counter.incrementAndGet(), String.format(template, name));
	}
}
