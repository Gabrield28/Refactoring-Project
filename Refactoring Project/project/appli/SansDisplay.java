package appli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SansDisplay {

	public void noNameDisp(File file) throws IOException{

		String filepath= "./Ressources/sortie/visu.css";
		FileInputStream fis = new FileInputStream(filepath);
		InputStreamReader input = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(input);
		String data;
		String result = new String();
		int lineNumber=0;
		int i=0;

		while ((data = br.readLine()) != null) {
			i++;
			if(data.contains("/*1*/	display : none;")) {
				lineNumber=i;
				break;
			}
			else if(data.contains("/*Modif1*/")) {
				lineNumber=i;
				break;
			}
			result = result.concat(data + "\n");
		}
		br.close();
		//lineNumber=lineNumber+1;
		String Mystring ="/*Modif1*/";
		String Mystringline = Files.readAllLines(Paths.get(filepath)).get(lineNumber-1); // get method count from 0 so -1
		if(!Mystringline.equalsIgnoreCase(Mystring)) {
			setVariable(lineNumber, Mystring, filepath);
		}else {
		}		
	}

	public void noDureeDisp(File file) throws IOException{

		String filepath= "./Ressources/sortie/visu.css";
		FileInputStream fis = new FileInputStream(filepath);
		InputStreamReader input = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(input);
		String data;
		String result = new String();
		int lineNumber=0;
		int i=0;

		while ((data = br.readLine()) != null) {
			i++;
			if(data.contains("/*2*/	display : none;")) {
				lineNumber=i;
				break;
			}
			else if(data.contains("/*Modif2*/")) {
				lineNumber=i;
				break;
			}
			result = result.concat(data + "\n");
		}
		br.close();
		//lineNumber=lineNumber+1;
		String Mystring ="/*Modif2*/";
		String Mystringline = Files.readAllLines(Paths.get(filepath)).get(lineNumber-1); // get method count from 0 so -1
		if(!Mystringline.equalsIgnoreCase(Mystring)) {
			setVariable(lineNumber, Mystring, filepath);
		}else {
		}		
	}
	
	public void noIdDisp(File file) throws IOException{

		String filepath= "./Ressources/sortie/visu.css";
		FileInputStream fis = new FileInputStream(filepath);
		InputStreamReader input = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(input);
		String data;
		String result = new String();
		int lineNumber=0;
		int i=0;

		while ((data = br.readLine()) != null) {
			i++;
			if(data.contains("/*3*/	display : none;")) {
				lineNumber=i;
				break;
			}
			else if(data.contains("/*Modif3*/")) {
				lineNumber=i;
				break;
			}
			result = result.concat(data + "\n");
		}
		br.close();
		//lineNumber=lineNumber+1;
		String Mystring ="/*Modif3*/";
		String Mystringline = Files.readAllLines(Paths.get(filepath)).get(lineNumber-1); // get method count from 0 so -1
		if(!Mystringline.equalsIgnoreCase(Mystring)) {
			setVariable(lineNumber, Mystring, filepath);
		}else {
		}		
	}

	public static void setVariable(int lineNumber, String data, String filepath) throws IOException {
		Path path = Paths.get(filepath);
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		lines.set(lineNumber - 1, data);
		Files.write(path, lines, StandardCharsets.UTF_8);
	}

}
