import java.util.*;


public class Day17 {

	public static void main(String[] args) {
	
		LinkedList<Integer> buff = new LinkedList<Integer>();
		int curr = 0;
		buff.add(0);
		int b = 0;
		for(int i=1;i<50000001;i++){
			curr = (curr + 345) % i;
			if(curr == 0) {
				b = i;
			}
			curr++;
		}
		
		curr = 0;

		for(int i = 1;i<2018;i++){
			curr = (curr + 345) % i;
			buff.add(curr+1,i);
			curr++;
		}
		System.out.println("Part A: "+buff.get((curr+1) % buff.size()));
		System.out.println("Part B: "+b);
	}
}
