package com.spring.poc.spring.integration.scheduler;

import java.util.Properties;

import com.spring.poc.spring.scheduler.service.ContextService;
import com.spring.poc.spring.scheduler.service.ContextServiceFactory;
import com.spring.poc.spring.scheduler.service.ContextTypes;

public class Application {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
	
		Properties properties = new Properties();
		properties.put("sample.key", "not.used.value");
		ContextServiceFactory.startNewContext(properties , "corn", "cornContext", ContextTypes.APPTEST);
		
		ContextServiceFactory.startNewContext(properties , "default", "defaultContext", ContextTypes.APPTEST);
		
		System.out.println("Following contexts are registered: "+ContextServiceFactory.getAllRegisteredContextNames());
		
		Thread.sleep(20000);
		System.out.println("Default context removed : "+ContextService.removeAppContext("defaultContext"));
		System.out.println("After deleting registered contexts : "+ContextServiceFactory.getAllRegisteredContextNames());
		
	}
	
	
	
	public void testContext(){
		ContextService service = new ContextService();
		String [] locations = service.configLocations(ContextTypes.APPTEST);
		for(String str:locations){
			System.out.println(str);
		}
	}

}
