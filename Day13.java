import java.util.*;
import java.io.*;

public class Day13 {
	
	public static void main(String[] args) {
		List<Integer> fw = new ArrayList<Integer>();
		for(int i =0;i<87;i++) {
			fw.add(0);
		}
		try (BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			while((line = br.readLine()) != null) {
				String[] splitLine = line.split(": ");
				fw.set(Integer.parseInt(splitLine[0]),									Integer.parseInt(splitLine[1])
					);
			}
		} catch (Exception e ) {
			e.printStackTrace();
		}

		int severity = 0;
		boolean caught = true;
		int delay = 0;
		
		while(caught) {
			caught = false;
			for(int i = 0; i<fw.size();i++) {
				if(fw.get(i) > 0) {
					int mod = fw.get(i) * 2 - 2;
					if ( (i + delay) % mod == 0) {
						caught = true;
						break;
					}
				}
			}
			if(caught){
				delay++;
			}
		}
		System.out.println("Not caught after, delay " + delay);
	}
}
