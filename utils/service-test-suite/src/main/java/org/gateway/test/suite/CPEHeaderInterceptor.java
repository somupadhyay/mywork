/**
 * 
 */
package org.gateway.test.suite;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

/**
 * @author somnath
 *
 */
public class CPEHeaderInterceptor implements ClientHttpRequestInterceptor {

	private Map<String, String> headers;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		request.getHeaders().setAll(headers);
		return execution.execute(request, body);
	}

	/**
	 * @param headers
	 */
	public CPEHeaderInterceptor(Map<String, String> headers) {
		super();
		this.headers = headers;
	}
	
	@SuppressWarnings("unused")
	private CPEHeaderInterceptor(){}

	public static class Builder {

		private Map<String, String> headers;
		
		private static final String AUTHORIZATION = "Authorization";
		private static final String BASIC = "Basic ";
		private static final String BEARER = "Bearer ";
		private static final String CONTENT_TYPE = "Content-Type";
		private static final String ACCEPT = "Accept";
		
		public static Builder fromBasicAuth(String username, String password) {
			Builder builder = new Builder();
			Map<String, String> _headers = new HashMap<String, String>();
			String token = username+ ":"+password;
			String authToken = BASIC+ Base64.getEncoder().encodeToString(token.getBytes());
			_headers.put(AUTHORIZATION, authToken);
			builder.setHeaders(_headers);
			return builder;
		}
		
		public static Builder fromHeaders(Map<String, String> headers) {
			Builder builder = new Builder();
			builder.setHeaders(headers);
			return builder;
		}
		
		public Builder headers(Map<String, String> headers){
			this.headers.putAll(headers);
			return this;
		}
		
		public Builder oAuthToken(String token){
			this.headers.put(AUTHORIZATION, BEARER+ token);
			return this;
		}
		
		public Builder contentType(String contentType){
			this.headers.put(CONTENT_TYPE, contentType);
			return this;
		}
		
		public Builder assepts(String accepts){
			this.headers.put(ACCEPT, accepts);
			return this;
		}
		
		public Builder basicAuthToken(String token){
			this.headers.put(AUTHORIZATION, BASIC+ token);
			return this;
		}

		
		public CPEHeaderInterceptor build(){
			Assert.notNull(this.headers, "Header must not be null");
			return new CPEHeaderInterceptor(this.headers);
		}
		
		/**
		 * @return the headers
		 */
		public Map<String, String> getHeaders() {
			return headers;
		}

		/**
		 * @param headers the headers to set
		 */
		public void setHeaders(Map<String, String> headers) {
			this.headers = headers;
		}
		
		
	}
}
