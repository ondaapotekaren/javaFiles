import java.util.*;
import java.io.*;

public class Day11 {

	public static int[] sortIndex (int[] input) {
		// indexlist, sorted by largest value at index.
		int indexList[] = new int[input.length];
		int index = 0;
		int[] dirs = input.clone();
		for(int i = 0;i<dirs.length;i++){
			int largest = -1;
			for(int j = 0; j<dirs.length;j++) {
				if (dirs[j] > largest){
					index = j;
					largest = dirs[j];
				}
			}
			dirs[index] = -1;
			indexList[i] = index;
		}
		return indexList;
	}

	public static void main(String[] args){
		System.out.println("Day 11");
		/*
		int test = Integer.parseInt(args[0]);
		int res = test > 10 ? 100 : 0;
		System.out.println(res);
		*/
		
		// count occur of ne,ne..
		// then ifor each occ of s, do nothing.
		String[] splitline = {""};
		try (BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			while((line = br.readLine()) != null) {
				splitline = line.split(",");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Day11 day11 = new Day11();
		int[] dirs = new int[6];
		// 0 = n, 1 = ne etc
		//n = nw = ne = s = sw = se = 0;
		for(String ss : splitline) {

			if (ss.equals("n")) {
				dirs[0] = dirs[0] + 1;
			}
			else if (ss.equals("ne")) {
				dirs[1] = dirs[1] + 1;
			}
			else if (ss.equals("se")) {
				dirs[2] = dirs[2] + 1;
			}
			else if (ss.equals("s")) {
				dirs[3] = dirs[3] + 1;
			}
			else if (ss.equals("sw")) {
				dirs[4] = dirs[4] + 1;
			}
			else if (ss.equals("nw")) {
				dirs[5] = dirs[5] + 1;
			}
		}


		/*
		for(int i : indexList) {
			System.out.println(i);
		}
		*/

		//System.out.println(index);
		// take the largest value and start reducing.
	
		// adjacent steps can not be reduced.		
		int[] indexList = sortIndex(dirs);
		dirs[indexList[0]] = dirs[indexList[0]] - dirs[(indexList[0] + 3)  % 6];
		

		for(int i : dirs ){
			System.out.println(i);
		}
				

		// reduce adjacents opposites.	
		//if( dirs[(index+1) % 6] > d)
		// reduce to right
		
		// reduce to left
			
		
		//System.out.println(dirs[index]);
		
		//reduce 	
	}

}
