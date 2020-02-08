package com.learnjava.beginner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class CountWordsinaString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length > 0) {
			String filepath = args[0];
			String content = CountWordsinaString.readfile(filepath);
			int count = CountWordsinaString.countwords(content);
			System.out.println("The number of words is: " + count);
		}else {
			System.out.println("Type in text to count and press enter");
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();
			
			int count = CountWordsinaString.countwords(line);
			System.out.println("The number of words is: " + count);
		}
		
	}
	private static int countwords(String text ) {
		int count = 0;
		String[] splitted = text.split("\\s+");
		return splitted.length;
	}

	private static String readfile(String filepath) {
		String lines = "";
		try{     
			String content = new String(Files.readAllBytes(Paths.get(filepath)));
		    // do something with everything string
		    lines += content;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
		
	}
}
