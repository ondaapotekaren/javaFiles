import java.util.*;

public class Day6 {
	public static void main(String[] args) {

		System.out.println("Day 6");
		List<Integer> banks = new ArrayList<Integer>
					(Arrays.asList(10,3,15,10,5,15,5,15,9,2,5,8,5,2,3,6)); 
		//List<Integer> banks = new ArrayList<Integer>(Arrays.asList(0,2,7,0));
		List<String> records = new ArrayList<String>();
		List<Double> doubleRec = new ArrayList<Double>();
		String currentRep;
		boolean match = false; 
		int cycles = 0;
		int rest;
		while (!match) {
			
			rest = Collections.max(banks);
			
			int index = 0;

			while(index < banks.size()){
				if(rest == banks.get(index)) {
					banks.set(index,0);
					break;
				}
				index++;
			}
			
			while(rest > 0){
				index = (index + 1) % banks.size();
				banks.set(index,banks.get(index)+1);
				rest--;
			}

			cycles++;
				
			currentRep = "";
			for(Integer bank : banks){
				currentRep = currentRep + "." + bank;	
			}
			//System.out.println(currentRep);
			// comp stuff
			for (String s : records) {
				if (currentRep.equals(s)) {
					match = true;
					break;
				}
			}
			
			records.add(currentRep);	
		}
		int loopSize = 0;
		String cmp = records.get(records.size()-1);
		for(int i = records.size()-2;i>-1;i--){
			loopSize++;
			if (cmp.equals(records.get(i)) ) {
				break;
			}
		}	
		System.out.println("Part A answer: "+cycles);
		System.out.println("Part B answer: "+loopSize);
	}
}

