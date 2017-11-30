package org.demo.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {


	@Value("${hello.message:Local hello}")
	private String message;
	
	@GetMapping("/hello")
	public String sayHello(){
		return message;
	}
}
