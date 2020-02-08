package com.learnjava.beginner;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountVowels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Please type in a text");
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		int numberofvowels = CountVowels.countVowelsPerWord(text);
		String vowelreport = CountVowels.generatevowelreport(text);
		
		System.out.println("The number of vowels in your text above is : " + numberofvowels);
		System.out.println(vowelreport);
	}

	
	private static int countVowelsPerWord(String word) {
		int count = 0;
		for(int i = 0; i < word.length(); i++) {
			char current_char = word.charAt(i);
			if(CountVowels.characterisvowel(current_char)) {
				count += 1;
			}
		}
		return count;
	}
	private static boolean characterisvowel(char character) {
		String vowels = "aeiou";
		boolean isvowel = false;
		if(vowels.indexOf(character) >= 0) {
			isvowel = true;
		}
		
		return isvowel;
	}
	
	private static String generatevowelreport(String text) {
		String report_text = "Here is your vowel report: \n";
		HashMap<Character, Integer> report = new HashMap<Character, Integer>();
		
		for(int i = 0; i < text.length(); i++) {
			char current_char = text.charAt(i);
			if(report.containsKey(current_char)) {
				int newnumber = report.get(current_char);
				newnumber += 1;
				report.put(current_char, newnumber);
			}
			else if(CountVowels.characterisvowel(current_char)) {
				report.put(current_char, 1);
			}
		}
		
		
		for(Map.Entry<Character, Integer> eachvowel : report.entrySet()) {
			 char vowel = eachvowel.getKey();
			 int value = eachvowel.getValue();
			 report_text += vowel +  " occurs " + value + " times \n";
		}
		return report_text;
	}
}
