package com.learnjava.beginner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PigLatin {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0) {
			System.out.println("Provide a word or a sentence an press Enter");
			Scanner sc = new Scanner(System.in);
			String text = sc.nextLine();
			String piglatinized = PigLatin.wordToLatin(text);
			System.out.println("Your Pig Latin is : " + piglatinized);
		}else {
			System.out.println("Your piglatinized words are: ");
			for(String word : args) {
				String piglatin = PigLatin.wordToLatin(word);
				System.out.println("Word: " + word + " ---- " + piglatin);
			}
		}
		
	}
	
	private static String wordToLatin(String word) {
		
		String latin = "";
		boolean isvowel  = PigLatin.firstCharisVowel(word.charAt(0));
		if(isvowel) {
			latin += word + "ay";
		}
		else {
			String consonantcluster = PigLatin.getConsonantCluster(word);
			latin += word.substring(word.indexOf(consonantcluster) +  consonantcluster.length()) + consonantcluster + "ay";
		}
		return latin;
	}
	private static String getConsonantCluster(String word) {
		String consonantcluster = "";
		for(int i = 0; i < word.length(); i++) {
			char current_char = word.charAt(i);
			if(PigLatin.firstCharisVowel(current_char)) {
				break;
			}else {
				consonantcluster += current_char;
			}
		}
		return consonantcluster;
	}
	private static boolean firstCharisVowel(char firstcharacter) {
			
			boolean isvowel = false;
			String vowels = "aeiou";
			for(int i = 0; i < vowels.length(); i++) {
				char character = vowels.charAt(i);
				if(character == firstcharacter) {
					isvowel = true;
					break;
				}
			}
					
			return isvowel;
	}
}
