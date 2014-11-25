package com.wadl.util.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.wadl.util.wadl.generator.jaxb.WadlApplication;
import com.wadl.util.wadl.generator.jaxb.WadlDoc;
import com.wadl.util.wadl.generator.jaxb.WadlMethod;
import com.wadl.util.wadl.generator.jaxb.WadlParam;
import com.wadl.util.wadl.generator.jaxb.WadlParamStyle;
import com.wadl.util.wadl.generator.jaxb.WadlRepresentation;
import com.wadl.util.wadl.generator.jaxb.WadlRequest;
import com.wadl.util.wadl.generator.jaxb.WadlResource;
import com.wadl.util.wadl.generator.jaxb.WadlResources;
import com.wadl.util.wadl.generator.jaxb.WadlResponse;

@Controller
public class WadlController {

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@Autowired
	private ParameterNameDiscoverer parameterNameDiscoverer;

	//private static final Logger LOGGER = Logger.getLogger(WadlController.class);

	/**
	 * Generates the WADL dynamically by scanning all the request URI exposed in
	 * project. Once web-service deployed enter
	 * http://{hostname}:{port}/{contextpath}/wadl to access WADL.
	 * 
	 * @param HttpServletRequest
	 * @return WadlApplication
	 */
	@RequestMapping(value = "/wadl", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody
	WadlApplication generateWadl(HttpServletRequest request) {
	//	LOGGER.debug("inside generateWadl method");
		WadlApplication result = new WadlApplication();
		WadlDoc doc = new WadlDoc();
		doc.setTitle("REST Service WADL v1.0");
		result.getDoc().add(doc);
		WadlResources wadResources = new WadlResources();
		wadResources.setBase(getBaseUrl(request));

		Map<RequestMappingInfo, HandlerMethod> handletMethods = handlerMapping
				.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handletMethods
				.entrySet()) {
			WadlResource wadlResource = new WadlResource();

			HandlerMethod handlerMethod = entry.getValue();
			RequestMappingInfo mappingInfo = entry.getKey();

			Set<String> pattern = mappingInfo.getPatternsCondition()
					.getPatterns();
			Set<RequestMethod> httpMethods = mappingInfo.getMethodsCondition()
					.getMethods();
			ProducesRequestCondition producesRequestCondition = mappingInfo
					.getProducesCondition();
			Set<MediaType> mediaTypes = producesRequestCondition
					.getProducibleMediaTypes();

			for (RequestMethod httpMethod : httpMethods) {
				WadlMethod wadlMethod = new WadlMethod();

				for (String uri : pattern) {
					wadlResource.setPath(uri);
				}
				// Method
				wadlMethod.setName(httpMethod.name());
				Method javaMethod = handlerMethod.getMethod();
				wadlMethod.setId(javaMethod.getName());
				WadlDoc wadlDocMethod = new WadlDoc();
				wadlDocMethod.setTitle(javaMethod.getDeclaringClass().getName()
						+ "." + javaMethod.getName());
				wadlMethod.getDoc().add(wadlDocMethod);
				// Request
				WadlRequest wadlRequest = new WadlRequest();

			//	LOGGER.debug("sending to build request for: "
			//			+ handlerMethod.getMethod());

				buildRequest(javaMethod, wadlRequest, handlerMethod);

				if (!wadlRequest.getParam().isEmpty()) {
					wadlMethod.setRequest(wadlRequest);
				}

				// Response
				if (!mediaTypes.isEmpty()) {
					WadlResponse wadlResponse = new WadlResponse();
					wadlResponse.getStatus().add(200l);
					for (MediaType mediaType : mediaTypes) {
						WadlRepresentation wadlRepresentation = new WadlRepresentation();
						wadlRepresentation.setMediaType(mediaType.toString());
						wadlResponse.getRepresentation()
								.add(wadlRepresentation);
					}
					wadlMethod.getResponse().add(wadlResponse);
				}
				wadlResource.getMethodOrResource().add(wadlMethod);
			}
			wadResources.getResource().add(wadlResource);

		}
		result.getResources().add(wadResources);

		return result;
	}

	private String getBaseUrl(HttpServletRequest request) {
		String requestUri = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + requestUri;
	}

	/**
	 * Build the WADL param request by scanning individual handler method
	 * arguments annotation
	 * */
	private void buildRequest(Method method, WadlRequest wadlRequest,
			HandlerMethod handlerMethod) {
		// Param
		for (int i = 0; i < method.getParameterTypes().length; i++) {
			WadlParam waldParam = new WadlParam();
			String paramName = null;
			MethodParameter methodParam = new MethodParameter(method, i);
			methodParam
					.initParameterNameDiscovery(this.parameterNameDiscoverer);
		//	LOGGER.debug("Building wadlparam for : "
		//			+ methodParam.getParameterName());
			Annotation[] paramAnns = methodParam.getParameterAnnotations();
			for (Annotation paramAnn : paramAnns) {
				if (RequestParam.class.isInstance(paramAnn)) {
					RequestParam requestParam = (RequestParam) paramAnn;
					paramName = requestParam.value();
					waldParam.setStyle(WadlParamStyle.QUERY);
					waldParam.setRequired(requestParam.required());
				} else if (PathVariable.class.isInstance(paramAnn)) {
					PathVariable requestParam = (PathVariable) paramAnn;
					paramName = requestParam.value();
					waldParam.setStyle(WadlParamStyle.TEMPLATE);
				} else if (ModelAttribute.class.isInstance(paramAnn)) {
					ModelAttribute requestParam = (ModelAttribute) paramAnn;
					paramName = requestParam.value();
					waldParam.setStyle(WadlParamStyle.QUERY);
				}
				if (paramName == null || paramName.equals("")) {
					paramName = methodParam.getParameterName();
				}
				waldParam.setName(paramName);
			}
			if (waldParam != null) {
				wadlRequest.getParam().add(waldParam);
			}

			if (paramAnns.length == 0) {
				Method[] m = handlerMethod.getBeanType().getDeclaredMethods();
				for (Method method2 : m) {
					if (method2.getAnnotation(ModelAttribute.class) != null
							&& method2.getReturnType() == methodParam
									.getParameterType()) {
						buildRequest(method2, wadlRequest, handlerMethod);
					}
				}
			}
		}
	}

}
