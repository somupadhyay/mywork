package com.spring.poc.spring.scheduler.service;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ContextService {
	
	private static final Logger LOGGER = Logger.getLogger(ContextService.class);
	private static final String BASE_LOCATION="META-INF/spring/spring-integration-scheduler";
	private static final String APP_CONFIG="application-configuration.xml";
	private static final String FILE_SEPERATOR="/";
	
	public ConfigurableApplicationContext getAppContext(ContextTypes contextTypes, String contextName) {
		LOGGER.warn("Creating a context for .. "+contextName);
		if(ContextUtil.contexts.containsKey(contextName)){
			return ContextUtil.contexts.get(contextName);
		}
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				configLocations(contextTypes), false);
		ctx.registerShutdownHook();
		LOGGER.warn("Context "+contextName+" created!");
		return ctx;

	}

	
	public static boolean removeAppContext(String contextName) {
		LOGGER.warn("Closing context. "+contextName);
		boolean flag = false;
		ConfigurableApplicationContext ctx = ContextUtil.contexts.get(contextName);
		if(null!=ctx){
			ctx.close();
			flag=true;
			ContextUtil.contexts.remove(contextName);
		}else{
			flag=false;
		}
		
		return flag;
	}
	
	public String[] configLocations(ContextTypes types){
		LOGGER.info("Resolving the configuration for "+types.toString());
		String[] configLocations =  {BASE_LOCATION+FILE_SEPERATOR+types.toString()+FILE_SEPERATOR+APP_CONFIG};
		return configLocations;
	}
}
