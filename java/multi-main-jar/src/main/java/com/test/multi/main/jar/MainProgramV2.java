package com.test.multi.main.jar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainProgramV2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException  {

		String [] state = new String[100];
		String [] city = new String[100];
		BufferedReader br = new BufferedReader(new FileReader(new File("/temp/tempfile.txt")));
		String line;
		while ((line=br.readLine())!= null)
		   { 
		    int i=0;
		    String [] tokens= new String[2];
		    tokens= line.split("-");
		    state[i]=tokens[0];
		    city[i]= tokens[1];
		    i++;
		    
		   }

		br.close();
	}

	
	
}
