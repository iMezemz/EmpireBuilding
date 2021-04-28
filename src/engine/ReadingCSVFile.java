package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadingCSVFile {
	public static ArrayList<String[]> readFile(String path) throws IOException {
		ArrayList<String[]> file = new ArrayList<String[]>();
		String currentLine = "";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			file.add(currentLine.split(","));
		}
		return file;
	}

	
}
