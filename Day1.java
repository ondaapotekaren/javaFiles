import java.util.*;
import java.util.stream.Stream;

public class Day1 {
	public static void main(String[] args) {
		ReadInputFile readInputFile = new ReadInputFile("./"+args[0]);
		String numberList = readInputFile.getStringList().get(0);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i = 0; i<numberList.length();i++) {
			numbers.add(
			Integer.parseInt(
			numberList.substring(i,i+1)));
		}
		ArrayList<Pair<Integer>> pairList = pair(numbers,1);
		System.out.println("Part A "+sumByCond(pairList));
		
		pairList = pair(numbers, numbers.size()/2);
		
		System.out.println("Part B "+sumByCond(pairList));
	}
	
	public static int sumByCond(ArrayList<Pair<Integer>> pairList) {
		return pairList.stream()
			.filter(p -> p.left.equals(p.right))
			.mapToInt(p ->  p.left)
			.sum();
	}


	public static ArrayList<Pair<Integer>> pair(ArrayList<Integer> al, int n) {
		ArrayList<Pair<Integer>> alp = new ArrayList<Pair<Integer>>();
		for(int i = 0;i<al.size();i++) {
			Pair<Integer> pair = new Pair<Integer>(al.get(i),al.get((i+n) % al.size()));
			alp.add(pair);
		}
		return alp;
	}
	
}


