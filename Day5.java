import java.util.*;
import java.io.*;

public class Day5 {

	public static void main(String[] args){
		List<Integer> inst = new ArrayList<Integer>();
		try(BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))){
			String line;
			while((line = br.readLine()) != null ) {
				inst.add(Integer.parseInt(line));
			}	
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int index = 0;
		int bound = inst.size();
		int steps = 0;
		if(args[1].equals("a")){
			while(index < bound && index > -1) {
				steps++;
			
				// offset
				int offset = inst.get(index);
				
				int tmp = index;
		
				// inc index with offset
				index = index + offset;

				// add 1 to instruc 
				inst.set(tmp,inst.get(tmp)+1);
			}
		} else {
			while(index < bound && index > -1){
				steps++;
				int offset = inst.get(index);
				int tmp = index;
				index = index + offset;
				if (offset >= 3){
					inst.set(tmp,inst.get(tmp)-1);	
				} else{
					inst.set(tmp,inst.get(tmp)+1);
				}
			}
		} 
		System.out.println(steps);
	}
}
