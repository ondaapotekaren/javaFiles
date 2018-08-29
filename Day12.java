import java.util.*;
import java.io.*;

public class Day12 {

	public static List<Integer> createGroup(Integer input) {
		List<Integer> group = new ArrayList<Integer>();
		int index = 0;
		group.add(input);
		while(index < group.size()){
			for(Integer i : graph.get(group.get(index))) {
				boolean inGroup = false;
				for(Integer j : group) {
					if ((int)i == (int)j){
						inGroup = true;
						break;
					}
				} 		
				if(!inGroup) {
					group.add(i);
				}			 		
			}
			index++;
		}
		return group;
	}
	
	static List<List<Integer>> graph = new LinkedList<List<Integer>>();

	public static void main(String[] args){
		for(int i= 0;i<2000;i++) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			graph.add(a);
		}
		try(BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) { 
			String line;
			while((line = br.readLine()) != null ) {
				String[] splitList = line.split(" <-> " );
				for (String s :  splitList[1].split(", ")) {
					graph.get( (int)Integer.parseInt(splitList[0]) )
						.add( (int)Integer.parseInt(s) );
				}
			} 
		} catch (Exception e ) {
			e.printStackTrace();
		}
	
		List<List<Integer>> groupList = new LinkedList<List<Integer>>();
		for(int i = 0;i<graph.size();i++) {
			boolean inGroup = false;
			for(List<Integer> l : groupList)
				for(Integer j : l) {
					if ((int) i == (int) j) {
						inGroup = true;
					}
				}
			if(!inGroup) {
				groupList.add(createGroup(i));
			}
		}
		System.out.println("Part A: "+groupList.get(0).size());
		System.out.println("Part B: "+groupList.size());
	}	
}
