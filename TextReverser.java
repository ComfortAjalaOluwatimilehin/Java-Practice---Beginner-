package com.learnjava.beginner;

import java.util.List;
import java.util.Scanner;

public class TextReverser {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Please type in something");
		String s = TextReverser.readString();
		String r = TextReverser.reverseString(s);
		System.out.println("Your reversed String is: " + r);
		String text = "My name is";
		text += " Comfort";
		System.out.println(text);

	}
	private static String readString() {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
	
		
		return line;
	}
	private static String reverseString(String input) {
		  String r = "";
		for(int i= input.length() - 1; i >= 0; i--) {	
			r += input.charAt(i);
		}
		
	return r;
	}

}
