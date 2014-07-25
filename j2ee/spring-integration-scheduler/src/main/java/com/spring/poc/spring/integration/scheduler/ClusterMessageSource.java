package com.spring.poc.spring.integration.scheduler;

import org.apache.log4j.Logger;
import org.springframework.integration.Message;
import org.springframework.integration.core.MessageSource;

public class ClusterMessageSource implements MessageSource<Object>{

	private static final Logger LOGGER = Logger.getLogger(ClusterMessageSource.class);

	public Message<Object> receive() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
