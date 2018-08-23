import java.util.*;
//import java.util.stream; 
import java.io.*;
public class Day4 {
	public static void main(String[] args){


	// stream doesn't seem to work	
	/*List<String> words = new ArrayList<String>();
	try (Stream<String> stream = Files.lines(Paths.get("passphrase"))) {

		stream.forEach(item -> words.add(item));
	}
	for(String s : words){
		System.out.println(s);
	}
	// read the input file line for line and count all non-double occurances.
	}*/
		int valid = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("./passphrase"))){
			String line;
			while((line = br.readLine()) != null ) {
				boolean foundMulti = false;	
				for (String s : line.split(" ")) { 
					int occInPhrase = 0;
					for(String ss : line.split(" ")){
						if(s.equals(ss)) {
							occInPhrase++;
						}
					}
					if(occInPhrase > 1){
						foundMulti = true;
						break;
					}
				}
				if (!foundMulti){
					valid++;
				}
				
			}

		} catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(valid);
	}
}
