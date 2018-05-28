/**
 * 
 */
package com.emc.it.cpe;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.emc.it.cpe.ws.WSClient;

/**
 * @author upadhs5
 *
 */

@RunWith(Parameterized.class) 
public class SoapServiceTest {

    private String endpoint;
    private String username;
    private String password;
    private String request;
    private String testCaseId;
    
   
    @Parameters
    public static Collection<?> spreadsheetData() throws IOException {
        ExcelReader reader = new ExcelReader("src/test/resources/soap-data.xlsx",5);
        List<String[]> records = reader.readExcel();
        return records;
    }

    public SoapServiceTest(String testCaseId, String endpoint, String username, String password, String request) {
        super();
        this.testCaseId = testCaseId;
        this.endpoint = endpoint;
        this.username = username;
        this.password = password;
        this.request = request;
        
    }

    @Test
    public void performTest() {
        this.request = this.request.replaceAll("\"\"", "\"");
        String response = WSClient.Builder.fromAuth(this.username, this.password).endpoint(this.endpoint).build()
                .callWService(this.request);
       TestCaseReport.soapTestReport.put(this.testCaseId, response);
        System.out.println(response);
    }
    
    @AfterClass
    public static void generateReport(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder("Test Run Date: "+ sdf.format(new Date())).append("\n\n");
        sb.append("----------------------------------------").append("\n\n");
        TestCaseReport.soapTestReport.forEach((key, value) ->{
            sb.append(key).append("\t").append(value).append("\n").append("\n");
        });
        FileWriter fw = null;
        try {
            fw = new FileWriter("target/soap-test-report"+System.currentTimeMillis()+".txt");
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
