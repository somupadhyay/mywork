package com.spring.poc.spring.integration.scheduler;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

public class OtherService implements MessageSource{

	/**
	 * @param args
	 */
	public void test(String str) {
		System.out.println(str);

	}

	public String getMessage(String code, Object[] args, String defaultMessage,
			Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMessage(String code, Object[] args, Locale locale)
			throws NoSuchMessageException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMessage(MessageSourceResolvable resolvable, Locale locale)
			throws NoSuchMessageException {
		// TODO Auto-generated method stub
		return null;
	}

}
