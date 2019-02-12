/**
 * 
 */
package org.gateway.test.suite;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author somnath
 *
 */

@RunWith(Parameterized.class) 
public class ApikeyAuthTest {
	
    private String endpoint;
    private String apikey;
    private String operation;
    private String request;
    private String testCaseId;
    private String contentType;
    private String header;
    private Map<String,String> headers;
    
   
    @Parameters
    public static Collection<?> spreadsheetData() throws IOException {
        ExcelReader reader = new ExcelReader("src/test/resources/apikey-data.xlsx",7);
        List<String[]> records = reader.readExcel();
        return records;
    }

    public ApikeyAuthTest(String testCaseId, String endpoint, String apikey, String operation, String request, String contentType, String header) {
        super();
        this.testCaseId = testCaseId;
        this.endpoint = endpoint;
        this.apikey = apikey;
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
        TestCaseReport.apiKeyTestReport.put(this.testCaseId, response);
        System.out.println(response);
    }
    
        
    private String performPostOpration() {
        RestTemplate restTemplate = RestTemplateUtils.newRestTemplate();
        headers.put("apikey", apikey);
        restTemplate.getInterceptors().add(CPEHeaderInterceptor.Builder.fromHeaders(headers).contentType(this.contentType).build());
        ResponseEntity<String> response = restTemplate.postForEntity(this.endpoint, request, String.class);
        String resp = " Status:  "+ response.getStatusCode() + "  response:  " + response.getBody();
        return resp;
    }

    private String performGetOpration() {
        RestTemplate restTemplate = RestTemplateUtils.newRestTemplate();
        headers.put("apikey", apikey);
        restTemplate.getInterceptors().add(CPEHeaderInterceptor.Builder.fromHeaders(headers).build());
        ResponseEntity<String> response = restTemplate.getForEntity(this.endpoint, String.class);
        String resp = " Status:  "+ response.getStatusCode() + "  response:  " + response.getBody();
        return resp;
    }

    @AfterClass
    public static void generateReport(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder("Test Run Date: "+ sdf.format(new Date())).append("\n\n");
        sb.append("----------------------------------------").append("\n\n");
        TestCaseReport.apiKeyTestReport.forEach((key, value) ->{
            sb.append(key).append("\t").append(value).append("\n").append("\n");
        });
        FileWriter fw = null;
        try {
            fw = new FileWriter("target/apikey-test-report"+System.currentTimeMillis()+".txt");
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
