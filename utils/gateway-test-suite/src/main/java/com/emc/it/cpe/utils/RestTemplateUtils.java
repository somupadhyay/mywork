/**
 * 
 */
package com.emc.it.cpe.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * @author upadhs5
 *
 */
public class RestTemplateUtils {

	public static RestTemplate newRestTemplate(boolean turnOffSSL) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        if(turnOffSSL){
        	requestFactory.setHttpClient(getHttpClient());
        }
        RestTemplate template = new RestTemplate(requestFactory);
        // Rest template should not throw exceptions on server errors, but return the entity to us
        template.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            protected boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });
        return template;
    }
	
	public static HttpClient getHttpClient(){
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.useSystemProperties();
		builder.setSSLHostnameVerifier((hostname, session) -> (session.getPeerHost().contains("isus.emc.com"))?true:false);
		return builder.build();
	}

}
