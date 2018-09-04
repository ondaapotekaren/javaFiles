import java.util.*;
import java.io.*;

public class Day16 {
	
	public static LinkedList<String> dance(String[] splitLine,LinkedList<String> ll) {
		for(String s : splitLine) {
			if (s.charAt(0) == 's') {
				for(int i = 0;i<Integer.parseInt(s.substring(1));i++){
					ll.add(0,ll.removeLast());
				}
			} else {
				String[] ss = s.substring(1).split("/");
				int i = 0;
				int j = 0;
				if (s.charAt(0) == 'x') {
					i = Integer.parseInt(ss[0]);
					j = Integer.parseInt(ss[1]);
			 	} else if (s.charAt(0) == 'p') {
					String a = ss[0].toLowerCase();
					String b = ss[1].toLowerCase();
					while (! ll.get(i).equals(a)) {
						i++;
					}
					while (! ll.get(j).equals(b)) {
						j++;
					}
				}
				String tmp = ll.get(i);
				ll.set(i,ll.get(j));
				ll.set(j,tmp);
			}
		}
				return ll;
	}

	public static LinkedList<String> initList(LinkedList<String> ll) {
		ll.add("a");ll.add("b");ll.add("c");ll.add("d");
		ll.add("e");ll.add("f");ll.add("g");ll.add("h");
		ll.add("i");ll.add("j");ll.add("k");ll.add("l");
		ll.add("m");ll.add("n");ll.add("o");ll.add("p");		
		return ll;
	}

	public static String conc(LinkedList<String> ll) {
		String output = "";
		for(String s : ll){
			output = output + s;
		}
		return output;
	}

	public static void main(String[] args){
		LinkedList<String> ll = new LinkedList<String>();
		ll = initList(ll);
		String[] splitLine = {""};	
		try (BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			while((line = br.readLine()) != null) {
				splitLine = line.split(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int c = 0;
		String output = "";	
		while(! output.equals("abcdefghijklmnop")) {
			
			ll = dance(splitLine,ll);
			output = conc(ll);
			c++;
		}
		
		c = 1000000000  % c;
		for(int i = 0;i<c;i++){
			ll = dance(splitLine,ll);
		}
		String outputB = conc(ll);
	
		LinkedList<String> lls = new LinkedList<String>();
		lls = initList(lls);

		String outputA = conc(dance(splitLine,lls));

		System.out.println("Part A: "+outputA);
		System.out.println("Part B: "+outputB);
		
	}
}
