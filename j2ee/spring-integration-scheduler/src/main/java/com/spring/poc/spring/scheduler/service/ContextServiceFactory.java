/**
 * 
 */
package com.spring.poc.spring.scheduler.service;

import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;


/**
 * @author Somnath.Upadhyay
 *
 */

public class ContextServiceFactory {

	private static final Logger LOGGER = Logger.getLogger(ContextServiceFactory.class);
	
	private Properties properties;
	private String profile;
	private String contextName;
	private ContextService contextService;
	/**
	 * @param properties
	 * @param profile
	 */
	@SuppressWarnings("unused")
	private ContextServiceFactory(){
		LOGGER.info("Overriding the default bean to properties loaded dynamically. ");
	}
	
	public static void startNewContext(Properties properties, String profile, String name, ContextTypes contextType) {
		ContextServiceFactory contextFactory = new ContextServiceFactory();
		contextFactory.properties = properties;
		contextFactory.profile = profile;
		contextFactory.contextName = name;
		contextFactory.contextService = new ContextService();
		contextFactory.generateContext(contextType);
	}


	private void generateContext(ContextTypes contextType) {
		StandardEnvironment env = new StandardEnvironment();
		ConfigurableApplicationContext context = contextService.getAppContext(contextType,(null!=contextName&&contextName.length()>0)?contextName:"default");
		
		PropertiesPropertySource pps = new PropertiesPropertySource(
				"ContextProperties", this.properties);
		env.getPropertySources().addLast(pps);
		context.setEnvironment(env);
		try{
			context.getEnvironment().setActiveProfiles(this.profile);
			context.refresh();
			ContextUtil.contexts.put(contextName, context);
		}catch(Exception e){
			LOGGER.error("Error creating context.");
			e.printStackTrace();
		}
		
	}
	
	public static Set<String> getAllRegisteredContextNames(){
		return ContextUtil.contexts.keySet();	
	}
	
}
