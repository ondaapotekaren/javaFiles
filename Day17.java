import java.util.*;


public class Day17 {

	public static void main(String[] args) {
	
		LinkedList<Integer> ll = new LinkedList<Integer>();
		int curr = 0;
		ll.add(0);
		for(int i = 1;i<30;i++){
			curr = (curr + 3) % ll.size();

			//System.out.println(curr);

			if(curr == ll.size()-1){
				ll.add(i);
				curr = ll.size()-1;
			} else {
				ll.add(curr+1,i);
				curr = curr +1;
			}
		
			String output = "";
			for(Integer j : ll){
				output = output + " " +Integer.toString(j);	
			}
			System.out.println(output);
		}
		//System.out.println(ll.get(1));
		/*
		for(Integer i : ll){
			System.out.println(i);
		}*/
		//System.out.println("Part A: "+ll.get(curr+1 % ll.size()));
	}
}
