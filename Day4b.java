import java.util.*;
import java.io.*;

public class Day4b {

	public static boolean areAnagrams(String s, String ss){

		if(s.length() != ss.length()){
			return false;
		}

		for(int i = 0; i < s.length();i++ ){

			char c = s.charAt(i);
			int m = 0;
			
			for(int j = 0;j < s.length();j++){
				if(c == s.charAt(j)){
					m++;
				}
			}

			int mm = 0;

			for(int j = 0; j < ss.length();j++){
				if(c == ss.charAt(j)){
					mm++;
				}
			}
			
			if(m != mm){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		int valid = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("./passphrase"))) {
			String line;
			while((line = br.readLine()) != null ) {
				boolean foundAnagrams = false;
				for (String s : line.split(" ")){
					int anagrams = 0;
					for(String ss : line.split(" ")){
						if (areAnagrams(s,ss)){
							anagrams++;
						}
					}
					if(anagrams > 1){
						foundAnagrams = true;
						break;
					}
				}
				if (!foundAnagrams){
					valid++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(valid);		
	}
}
