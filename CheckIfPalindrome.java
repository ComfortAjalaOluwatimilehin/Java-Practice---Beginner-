package com.learnjava.beginner;

import java.util.Scanner;

public class CheckIfPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Type in a text and press Ente");
		Scanner sc = new Scanner(System.in);
		String line  = sc.nextLine();
		String reverseline = CheckIfPalindrome.getreverse(line);
		String strippedline = CheckIfPalindrome.stripspaces(line);
		String strippedreverseline  = CheckIfPalindrome.stripspaces(reverseline);
		System.out.println("Your text is " + line);
		System.out.println("When stripped of all spaces: " + strippedline);
		System.out.println("The reverse form is " + reverseline);
		System.out.println("When stripped of all spaces: " + strippedreverseline);
		if(strippedreverseline.equalsIgnoreCase(strippedline)) {
			System.out.println("So your string is a palindrome");
		}else {
			System.out.println("You string isn't a palindrome.");
		}
	}
	
	private static String stripspaces(String text) {
		
		String stripped = text.replaceAll("[^a-zA-Z0-9]", "");
		return stripped;
		
	}
	
	private static String getreverse(String text) {
		
		String reverse = "";
		
		for(int i = text.length() - 1; i >= 0; i--) {
			
				char current = text.charAt(i);
				reverse += current;
		}
		
		return reverse;
	}

}
