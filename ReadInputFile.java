import java.util.*;
import java.io.*;

public class ReadInputFile {
	
	ArrayList<String> stringList;

	public ArrayList<String> read(String path) {
		ArrayList<String> s = new ArrayList<String>(); 
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				s.add(line);
			}		

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public ArrayList<String> getStringList() {
		return stringList;
	}
	
	ReadInputFile(String path) {
		stringList = read(path);
	}

}
