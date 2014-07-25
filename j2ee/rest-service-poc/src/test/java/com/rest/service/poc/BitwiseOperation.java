package com.rest.service.poc;

import java.util.Scanner;

public class BitwiseOperation {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BitwiseOperation bitOperation = new BitwiseOperation();
		System.out.println("Press 1 to 5 for following Operation");
		System.out.println("1 << operation");
		System.out.println("2 >> operation");
		System.out.println("3 ^  operation");
		Scanner scan = new Scanner(System.in);
		int operation =0;
		operation = scan.nextInt();
		
		switch(operation){
		case 1:
			bitOperation.leftShift();
			break;
		case 2:
			bitOperation.rightShift();
			break;
		case 3:
			bitOperation.exclusiveOR();
			break;
		default :
			System.out.println("wrong operation selected... please rerun and select proper one");
			break;
			
		}
	}

	private void exclusiveOR() {
		System.out.println("Please enter first Integer");
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		System.out.println("Please enter Second Integer");
		int y = scan.nextInt();
		System.out.println("Please wait while we perform  "+x+"^"+y+"  Operation... ");
		int z = x^y;
		System.out.println("And the result is : "+z);
	}

	private void rightShift() {
		System.out.println("Please enter first Integer");
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		System.out.println("Please enter Second Integer");
		int y = scan.nextInt();
		System.out.println("Please wait while we perform  "+x+">>"+y+"  Operation... ");
		int z = x>>y;
		System.out.println("And the result is : "+z);
		
	}

	private void leftShift() {
		System.out.println("Please enter first Integer");
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		System.out.println("Please enter Second Integer");
		int y = scan.nextInt();
		System.out.println("Please wait while we perform  "+x+"<<"+y+"  Operation... ");
		int z = x<<y;
		System.out.println("And the result is : "+z);
		
		
	}

}
