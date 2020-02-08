package com.learnjava.beginner;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TextEditor implements ActionListener {
	JFrame frame;
	JTextArea area;
	JButton button;
	TextEditor() {
		frame = new JFrame();
		area = new JTextArea();
		button = new JButton("save as text file");
		
		area.setBounds(0,0, 800, 700);
		button.setBounds(800 - 300, 800 - 80, 200, 50);
	
		button.addActionListener(this);
		frame.add(area);
		frame.add(button);
		frame.setSize(800, 800);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String text = area.getText();
		frame.dispose();
		frame.setVisible(false);
		System.out.println("type in the name of your text file below: ");
		Scanner sc = new Scanner(System.in);
		String filename  =sc.nextLine();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(text);
			writer.close();
			System.exit(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TextEditor();
	}
	
	
	

}
