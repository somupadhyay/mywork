package com.spring.security;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.message.GenericMessage;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	@Qualifier("messagingTemplate")
	private MessagingTemplate messagingTemplate;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value="/throwError", method=RequestMethod.GET)
	public String throwError(Locale locale, Model model, Principal principal){
		Message<String> msg = MessageBuilder.withPayload(locale.getCountry()).build();
	//	messagingTemplate.send(msg);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		//logger.info("Principal." +principal.getName());
		model.addAttribute("serverTime", formattedDate );
		//model.addAttribute("yourName",principal.getName());
		
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Principal principal) {
		logger.info("Welcome home! The client locale is {}.", locale);
		

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		Properties properties = new Properties();
		properties.put("hobby", "cricket");
		properties.put("music", "rock");
		properties.put("nationality", "indian");
		
		TestObject testObject = new TestObject();
		testObject.setId(1);
		testObject.setName("object1");
		testObject.setStatus("true");
		testObject.setProperties(properties);
		
		Properties properties1 = new Properties();
		properties1.put("hobby", "football");
		properties.put("music", "pop");
		properties.put("nationality", "nepali");
		
		TestObject testObject1 = new TestObject();
		testObject1.setId(2);
		testObject1.setName("shared");
		testObject1.setStatus("true");
		testObject1.setProperties(properties1);
		
		Map<String, TestObject> testObjectMap = new HashMap<String, TestObject>();
		testObjectMap.put(testObject.getName(), testObject);
		testObjectMap.put(testObject1.getName(), testObject1);
		
		model.addAttribute("adaptors", testObjectMap);
		
		return "home";
	}
	
	@RequestMapping(value = "/secure/secureView", method = RequestMethod.GET)
	public String secureView(Locale locale, Model model, Principal principal) {
		logger.info("Welcome Secure View! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		logger.info("Principal." +principal.getName());
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("yourName",principal.getName());
		
		return "securedpage";
	}
	
	@RequestMapping(value = "/secure/extreme/secureView", method = RequestMethod.GET)
	public String extremeSecureView(Locale locale, Model model, Principal principal) {
		logger.info("Welcome Extreme Secure View! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		logger.info("Principal." +principal.getName());
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("yourName",principal.getName());
		
		return "extremepage";
	}
	
	@RequestMapping(value="/errorReport", method=RequestMethod.POST)
	public String reportView(HttpServletRequest request, HttpServletResponse response, String msg,Model model) throws ServletException, IOException{
		msg="sending message from error method";		
		model.addAttribute("message", msg);
		return "errorPage";
		
	}
	
	@RequestMapping(value="/errorpage", method=RequestMethod.POST)
	public String errorPage(HttpServletRequest request, HttpServletResponse response, String msg,Model model) throws IOException{
		msg="sending message from error method";		
		model.addAttribute("message", msg);
		
		response.sendRedirect("/security/errorReport");
		return "errorPage";
			
	}
	
}
