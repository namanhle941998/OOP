package create.property;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
	public static ArrayList<String> readFile(String filename, int startLine, int count){
		ArrayList<String> array = new ArrayList<String>();
		String line = null;
		try {
			FileReader fileReader = new FileReader(filename);
		
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			for (int i = 0; i < startLine - 1; i++) {
				line = bufferedReader.readLine();
			}
			int j = 0;
			for (int i = startLine - 1; i < startLine + count - 1; i++) {
				line = bufferedReader.readLine();
				array.add(j, line);
				j++;
			}
			bufferedReader.close();
		}
		catch(IOException ex) {
			System.out.println("I/O error");
		}
		return array;
	}
}
