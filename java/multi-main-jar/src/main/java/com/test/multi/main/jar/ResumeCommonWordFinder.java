package com.test.multi.main.jar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ResumeCommonWordFinder {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a file name : ");
		String line = scanner.nextLine();
		System.out.println("the entered string is : "+line);
		File file = new File(line);
		if(!file.exists() || file.isDirectory()){
			System.out.println("File doesnot exists or is directory..");
			System.exit(1);
		}
		
		TreeMap<String, Integer> frequencyMap = new TreeMap<String, Integer>();
		
		scanner = new Scanner(file);
		while(scanner.hasNextLine()){
		String line1 /*="";*/ = scanner.nextLine();
	//	BufferedReader br = new BufferedReader(new FileReader(file));
	//	while((line1=br.readLine())!=null){
			
			System.out.println(line1);
			StringTokenizer parser = new StringTokenizer(line1, " \t\n\r\f.,;:!?'\"");
			while(parser.hasMoreTokens()){
				String currentWord = parser.nextToken();
				Integer frequencey = frequencyMap.get(currentWord);
				if(frequencey == null){
					frequencey = 0;
				}
				
				frequencyMap.put(currentWord, frequencey+1);
			}
			
		
		}
		System.out.println(frequencyMap);
	}

}
