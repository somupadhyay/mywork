/**
 * 
 */
package com.ng2.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author upadhs5
 *
 */
@RestController
public class TestController {

	@RequestMapping(path = "/hello", method=RequestMethod.GET)
	public Map<String, String> getMessage(){
		Map<String, String> message = new HashMap<String, String>();
		message.put("message", "Welcome!");
		message.put("status", "success");
		return message;
	}
}
