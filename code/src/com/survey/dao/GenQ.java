package com.survey.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GenQ {
	public static void main(String[] args) throws Exception {
		int id=1;
		File f =new File("questions.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			//System.out.println(line);
			line = line.trim();
			line = line.replace('.', ',');
			String parsed[] = line.split(",");
			System.out.printf("INSERT INTO survey.question (id, question, decision_factor, question_number) VALUES (%s,'%s',%s,%s);\n", new String[]{""+id, parsed[2], parsed[0], parsed[1]});
			id++;
			line = br.readLine();
		}
			
	}
}
