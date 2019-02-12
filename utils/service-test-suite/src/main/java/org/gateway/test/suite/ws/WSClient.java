/**
 * 
 */
package org.gateway.test.suite.ws;

import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.xmlbeans.XmlBeansMarshaller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.xml.transform.StringResult;

/**
 * @author somnath
 *
 */
public class WSClient {

	private WebServiceTemplate wsTemplate;

	public WSClient(WebServiceTemplate wsTemplate) {
		Assert.notNull(wsTemplate, "WebService Template must not be null");
		this.wsTemplate = wsTemplate;
	}

	public String callWService(String request) {
		StringResult responseResult = new StringResult();
		Source requestPayload = new StreamSource(new StringReader(request));
		wsTemplate.sendSourceAndReceiveToResult(requestPayload, responseResult);
		return responseResult.toString();
	}

	private WSClient() {
	}

	public static class Builder {
		private String username;
		private String password;
		private String endpoint;

		public static Builder fromAuth(String username, String password) {
			Builder builder = new Builder();
			builder.username = username;
			builder.password = password;
			return builder;
		}

		public static Builder fromEndpoint(String endpoint) {
			Builder builder = new Builder();
			builder.endpoint = endpoint;
			return builder;
		}

		
		public Builder endpoint(String endpoint) {
			this.endpoint = endpoint;
			return this;
		}

		public WSClient build() {
			if(StringUtils.isEmpty(username)){
				return new WSClient(wsTemplateNoSecurity());
			}
			return new WSClient(wsTemplate());
		}

		private WebServiceTemplate wsTemplate() {
			WebServiceTemplate wsTemplate = new WebServiceTemplate();
			wsTemplate.setDefaultUri(this.endpoint);
			wsTemplate.setInterceptors(getInterceptor());
			wsTemplate.setUnmarshaller(getXBeanMarshaller());
			wsTemplate.setMarshaller(getXBeanMarshaller());
			return wsTemplate;
		}

		private WebServiceTemplate wsTemplateNoSecurity() {
			WebServiceTemplate wsTemplate = new WebServiceTemplate();
			wsTemplate.setDefaultUri(this.endpoint);
		    wsTemplate.setUnmarshaller(getXBeanMarshaller());
			wsTemplate.setMarshaller(getXBeanMarshaller());
			return wsTemplate;
		}
		private XmlBeansMarshaller getXBeanMarshaller() {
			return new XmlBeansMarshaller();
		}

		private Wss4jSecurityInterceptor[] getInterceptor() {
			Wss4jSecurityInterceptor[] interceptors = new Wss4jSecurityInterceptor[1];
			Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
			interceptor.setSecurementActions("UsernameToken");
			interceptor.setSecurementUsername(this.username);
			interceptor.setSecurementPassword(this.password);
			interceptor.setSecurementPasswordType("PasswordText");
			interceptors[0] = interceptor;
			return interceptors;
		}
	}
}
