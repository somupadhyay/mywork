package com.html.parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkLister {

	/**
	 * @param args
	 * @throws IOException 
	 */
	private static final String []baseUrls = {"http://somehost.com:8080"/*,"http://somehost.com:8080"*/};
	private static final String context = "/somelog/9091.jsp";
	private static final String expression = ".log";
	private static final String CAUSED_BY = "Caused by:";
	private static final String ERROR_EXPRESSION = "ERROR";
	private static final Set<String> LOGLOCATIONS = new HashSet<String>();
	private static String baseUrl="";
	
	static{
		LOGLOCATIONS.add("[some-location]");
	}
	//.log
	
	public static void main(String[] args) throws IOException {
		Map<String, String> logFileLink = new HashMap<String, String>();		
		for(String baseLocalURL:baseUrls){
			setBaseUrl(baseLocalURL);
			logFileLink = getLogFileLink(baseUrl+context, 0);
			System.out.println(logFileLink);
		}
		
		for (String logFile : logFileLink.keySet()) {
			
			String logFileLinkStr = logFileLink.get(logFile);
			System.out.println("log File Name :" +logFile+"\n link :"+logFileLinkStr);
			String contents = getLogLines(logFileLinkStr);
			String calculatedBaseUrl = logFileLinkStr.substring(0,logFileLinkStr.indexOf("?"));
			System.out.println(calculatedBaseUrl);
			
				
		}
		/*Document doc = Jsoup.connect(baseUrl+context).get();
		Elements links = doc.select("a[href]");
		
		for (Element link : links) {
 			// get the value from href attribute
			String nextLink = link.attr("href");
			String linkText =  link.text();
			System.out.println("\nlink : " + nextLink);
			System.out.println("text : " + linkText);
			if(linkText.contains(expression)){
				getFirstLines(baseUrl+nextLink);
			}
 
		}
		
*/		
	}
	
	

	private static void generateFile(
			Map<String, Map<String, String>> baseUrlLogFileContent) {
		// TODO Auto-generated method stub
		
	}



	private static void setBaseUrl(String baseLocalURL) {
		baseUrl = baseLocalURL;
	}



	private static Map<String, String> getLogFileLink(String linkUrl , int count) {
		Map<String, String> linkMap = new HashMap<String, String>();
		Document doc;
		if(count>0)
			return null;
		
		try {
			doc = Jsoup.connect(linkUrl).get();
			Elements links = doc.select("a[href]");
			
			for (Element link : links) {
				String nextLink = link.attr("href");
				//System.out.println("Link: "+nextLink);
				String linkText =  link.text();
				System.out.println("linkText :"+linkText);
				if(linkText.contains("logs/") && count<=0){
					count++;
					Map<String, String> maps = getLogFileLink(baseUrl + nextLink, count);
					if(null!=maps){
						System.out.println(maps);
						linkMap.putAll(maps);
					}
				}else if(LOGLOCATIONS.contains(linkText)){
					System.out.println("next link : "+baseUrl + nextLink);
					Map<String, String> maps = getLogFileLink(baseUrl + nextLink, count);
					if(null!=maps){
						System.out.println(maps);
						linkMap.putAll(maps);
					}
				}else if(linkText.contains(expression)){
						linkMap.put(linkText, baseUrl + nextLink);
				}
			}
			System.out.println(linkMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return linkMap;
	}



	private static String getLogLines(String link) {
		System.out.println("Getting contents for : "+link);
		StringBuilder sbuilder = new StringBuilder();
		Document doc;
		try {
			Connection  connection = Jsoup.connect(link);
		 	connection.execute();
			byte[] responseByte = connection.response().bodyAsBytes();
			ByteArrayInputStream bais = new ByteArrayInputStream(responseByte);
			BufferedReader br = new BufferedReader(new InputStreamReader(bais));
			String str = "";
			while((str = br.readLine())!=null){
				if(str.contains(CAUSED_BY)){
					sbuilder.append(str).append("\n");
					sbuilder.append(br.readLine()).append("\n");
					sbuilder.append(br.readLine()).append("\n");
					sbuilder.append(br.readLine()).append("\n");
				}else if(str.contains(ERROR_EXPRESSION)){
					sbuilder.append(str).append("\n");
				}
			}
			System.out.println(sbuilder);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sbuilder.toString();
		
		
	}

}

class LogDTO{
	
	private String baseUrl;
	private String logFileName;
	private String logContents;
	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}
	/**
	 * @param baseUrl the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	/**
	 * @return the logFileName
	 */
	public String getLogFileName() {
		return logFileName;
	}
	/**
	 * @param logFileName the logFileName to set
	 */
	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}
	/**
	 * @return the logContents
	 */
	public String getLogContents() {
		return logContents;
	}
	/**
	 * @param logContents the logContents to set
	 */
	public void setLogContents(String logContents) {
		this.logContents = logContents;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LogDTO [baseUrl=" + baseUrl + ", logFileName=" + logFileName
				+ ", logContents=" + logContents + "]";
	}
	
	
}