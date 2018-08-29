import java.util.*;
import java.io.*;

public class Day11 {

	public static void main(String[] args) {
		int  x,y;
		x = y = 0;
		List<Integer> distances = new ArrayList<Integer>();
		String[] splitString = {""};
		try(BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			while((line = br.readLine()) != null) {
				splitString = line.split(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(String s : splitString){
			if (s.equals("n")) {
				y--;
				
			}
			else if (s.equals("nw")) {
				y--;
				x--;
			}
			else if (s.equals("sw")) {
				x--;
			}
			else if (s.equals("ne")) {
				x++;
			}
			else if (s.equals("se")) {
				y++;
				x++;
			}
			else if (s.equals("s")) {
				y++;
				
			}
			int steps;
			if (x*y <= 0) {
				steps = Math.abs(x) + Math.abs(y);
					
			} else {
				if (Math.abs(x) > Math.abs(y)) {
					steps = Math.abs(x);	
				} else {
					steps = Math.abs(y);
				}
			}
			distances.add(steps);
			
		}
		int max = -1;
		for (Integer i : distances) {
			if ((int)i > max){
				max = i;
			}
		}
		System.out.println("Part A: "+distances.get(distances.size()-1));
		System.out.println("Part B: "+max);
	}
}
