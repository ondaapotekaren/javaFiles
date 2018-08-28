import java.util.*;
import java.io.*;

public class Day9 {
	public static void main(String[] args) {
		System.out.println("Day 9");
		int acc = 0;
		int garbageCount = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			boolean garbage = false;
			while((line = br.readLine()) != null ) {
				int groupCount = 0;
				for(int i = 0;i<line.length();i++){
					char c = line.charAt(i);
					if (c == '!') {
						i++;
					}
					else if (c == '<' && !garbage) {
						garbage = true;
					}
					else if (c == '{' && !garbage) {
						groupCount++;
					}
					else if (c == '}' && !garbage) {
						acc = acc + groupCount;
						groupCount--;
					}
					else if (c == '>') {
						garbage = false;
					}
					else if (garbage) {
						garbageCount++;
					}
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(acc);
		System.out.println(garbageCount);
	}
}
