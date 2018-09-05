import java.util.*;


public class Day17 {

	public static void main(String[] args) {
	
		LinkedList<Integer> ll = new LinkedList<Integer>();
		int curr = 0;
		ll.add(0);
		int b = 0;
		for(int i=1;i<50000001;i++){
			curr = (curr + 345) % i;
				
			if(curr == 0) {
				b = i;
			}
			if (curr == i-1) {
				curr = i;
			} else {
				curr++;
			}
			
		}
		
		curr = 0;

		for(int i = 1;i<2018;i++){
			curr = (curr + 345) % ll.size();

			if(curr == ll.size()-1){
				ll.add(i);
				curr = ll.size()-1;
			} else {
				ll.add(curr+1,i);
				curr = curr +1;
			}
			
		}
		System.out.println("Part A: "+ll.get((curr+1) % ll.size()));
		System.out.println("Part B: "+b);
	}
}
