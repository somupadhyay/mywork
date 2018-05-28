/**
 * 
 */
package com.emc.it.cpe;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author upadhs5
 *
 */
@RunWith(Parameterized.class) 
public class OAuth2Test {
    
    private String endpoint;
    private String operation;
    private String request;
    private String testCaseId;
    private String contentType;
    private String client_id;
    private String client_secret;
    private String tokenurl;
    private String grant_type="client_credentials";
    private static final String TOKEN_KEY = "access_token";
    private String header;
    private Map<String,String> headers;
    
   
    @Parameters
    public static Collection<?> spreadsheetData() throws IOException {
        ExcelReader reader = new ExcelReader("src/test/resources/oauth-data.xlsx",9);
        List<String[]> records = reader.readExcel();
        return records;
    }

   // Id  endpoint    client_id   client_secret   tokenurl    Operation   request contentType

    public OAuth2Test(String testCaseId, String endpoint, String client_id, String client_secret, String tokenurl, String operation, String request, String contentType, String header) {
        super();
        this.testCaseId = testCaseId;
        this.endpoint = endpoint;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.tokenurl = tokenurl;
        this.operation = operation;
        this.request = request;
        this.contentType = contentType;
        this.header = header;
    }

    @Test
    public void performTest() throws JsonProcessingException, IOException {
        this.request = this.request.replaceAll("\"\"", "\"");
        String response = "Opration not supported";
        this.headers = new HashMap<>();
        if(!this.header.equalsIgnoreCase("NA")){
            this.header = this.header.replaceAll("\"\"", "\"");
            this.headers = JsonUtils.getStringMapOfJsonString(this.header);
        }
        if(this.operation.equalsIgnoreCase("GET")){
            response = performGetOpration();
        }else if(this.operation.equalsIgnoreCase("POST")){
            response = performPostOpration();
        }
        TestCaseReport.oAuthTestReport.put(this.testCaseId, response);
        System.out.println(response);
    }
    
    
    
    private String performPostOpration() throws JsonProcessingException, IOException {
        RestTemplate restTemplate = RestTemplateUtils.newRestTemplate();
        String token = getToken();
        restTemplate.getInterceptors().add(CPEHeaderInterceptor.Builder.fromHeaders(this.headers).oAuthToken(token).contentType(this.contentType).build());
        ResponseEntity<String> response = restTemplate.postForEntity(this.endpoint, request, String.class);
        String resp = " Status:  "+ response.getStatusCode() + "  response:  " + response.getBody();
        return resp;
    }

    private String performGetOpration() throws JsonProcessingException, IOException {
        RestTemplate restTemplate = RestTemplateUtils.newRestTemplate();
        String token = getToken();
        restTemplate.getInterceptors().add(CPEHeaderInterceptor.Builder.fromHeaders(this.headers).oAuthToken(token).build());
        ResponseEntity<String> response = restTemplate.getForEntity(this.endpoint, String.class);
        String resp = " Status:  "+ response.getStatusCode() + "  response:  " + response.getBody();
        return resp;
    }
    
    private String getToken() throws JsonProcessingException, IOException{
        RestTemplate _restTemplate = RestTemplateUtils.newRestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        /**
         * "client_id":"l7xxc26a2c0585c94a32a54a0f6e22139765",
            "client_secret":"b8d35632422945c58866ee8aefc222cd",
            "grant_type":"client_credentials"
         */
        map.put("client_id", Arrays.asList(client_id));
        map.put("client_secret", Arrays.asList(client_secret));
        map.put("grant_type", Arrays.asList(grant_type));
        
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = _restTemplate.postForEntity(this.tokenurl, request , String.class);
        
        Assert.assertTrue("Failed to generate oauth token service returned <"+response.getStatusCode()+"> status code, expected is 200", response.getStatusCode().value()==200);
        return JsonUtils.getStringMapOfJsonString(response.getBody()).get(TOKEN_KEY);
    }

    @AfterClass
    public static void generateReport(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder("Test Run Date: "+ sdf.format(new Date())).append("\n\n");
        sb.append("----------------------------------------").append("\n\n");
        TestCaseReport.oAuthTestReport.forEach((key, value) ->{
            sb.append(key).append("\t").append(value).append("\n").append("\n");
        });
        FileWriter fw = null;
        try {
            fw = new FileWriter("target/oauth-test-report"+System.currentTimeMillis()+".txt");
            fw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null!=fw){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
