package com.emc.it.cpe;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
 
/**
 * 
 * @author upadhs5
 *
 */
public class JsonUtils {
 
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
 
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
 
    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();
 
        for (int index = 0; index < length; index++) {
            builder.append("a");
        }
 
        return builder.toString();
    }
    
    public static ObjectReader getReaderFor(Class<?> clazz, String dateFormat){
    	ObjectMapper mapper = new ObjectMapper();
    	DateFormat format = new SimpleDateFormat(dateFormat);
		mapper.setDateFormat(format);
		return mapper.reader(clazz);
    }
    
    public static ObjectReader getReaderFor(Class<?> clazz){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.reader(clazz);
    }
    
    public static ObjectMapper getObjectMapper(){
    	ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
    }
    
    public static Map<String, String> getStringMapOfJsonString(String payload) throws JsonProcessingException, IOException{
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.reader(new TypeReference<Map<String, String>>() {}).readValue(payload);
    }
}