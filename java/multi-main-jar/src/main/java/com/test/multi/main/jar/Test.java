package com.test.multi.main.jar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String [] states = new String[100];
		String [] citys = new String[100];
		FileReader fr = new FileReader("D:/temp/tempfile.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String line = "";
		 int i=0;
		while((line=br.readLine())!=null){
			    String [] tokens= new String[2];
			    tokens= line.split("-");
			    states[i]=tokens[0];
			    citys[i]= tokens[1];
			    i++;
		}
		for(String state:states){
			if(null != state)
				System.out.println(state);
		}
		for(String city:citys){
			if(null != city)
				System.out.println(city);
		}
		
		br.close();
		fr.close();
	}

}
