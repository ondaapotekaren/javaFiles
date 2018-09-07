import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.stream.*;

public class Day2 {

	public static void main(String[] args) {
		ReadInputFile readInputFile = new ReadInputFile("./"+args[0]);
		ArrayList<String> sl = readInputFile.getStringList();
		ArrayList<List<Integer>> rowList = parseStringListToListOfList(sl);
		
		List<Integer> min = rowList.stream()
				.map(al -> al.stream().min(Integer::compare).get())
				.collect(ArrayList::new, ArrayList::add,ArrayList::addAll);
		
		List<Integer> max = rowList.stream()
				.map(al-> al.stream().max(Integer::compare).get())
				.collect(ArrayList::new, ArrayList::add,ArrayList::addAll);
		
		System.out.println("Part A: " + IntStream.range(0,max.size())
						.map(i -> max.get(i) - min.get(i))
						.sum()
		);

		int sum = 0;
		for (List<Integer> li : rowList) {
			for (int i = 0;i<li.size();i++) {
				for (int ii = 0;ii<li.size();ii++) {
					if (li.get(ii) % li.get(i) == 0 && i != ii) {
						sum = sum + li.get(ii) / li.get(i);
					}	
				}
			}
		}
		
		System.out.println("Part B "+ sum);
	//	System.out.println("k: "+sum1);
	}


	public static ArrayList<String> separateDigits(String line) {
		ArrayList<String> splitLine = new ArrayList<String>();
		for (int i = 0;i<line.length();i++) {		
			String output = "";
			while(i<line.length() && (Character.isDigit(line.charAt(i)))) {
				output = output + line.substring(i,i+1);
				i++;
			}
			splitLine.add(output);
			
		}
		return splitLine;
	}

	public static ArrayList<List<Integer>> parseStringListToListOfList(ArrayList<String> sl) {
		ArrayList<List<Integer>> twoDimList = new ArrayList<List<Integer>>();
		for(String line : sl) {
			ArrayList<String> splitLine = separateDigits(line);
			ArrayList<Integer> oneDimList = new ArrayList<Integer>();
			for(String s : splitLine) {
				oneDimList.add(Integer.parseInt(s));
			}
			twoDimList.add(oneDimList);
		}
		return twoDimList;
	}
}
