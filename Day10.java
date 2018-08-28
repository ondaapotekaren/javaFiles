import java.util.*;
import java.io.*;

public class Day10 {

	public static void main(String[] args) {
		System.out.println("Day 10");
		//System.out.println(Integer.parseInt(args[0]) % 5);
		int[] lens = {63,144,180,149,1,255,167,84,125,65,188,0,2,254,229,24};
		//int[] lens = {3,4,1,5};
		int[] knot = new int[256];
		//int[] knot = new int[5];
		int skipSize = 0;
		int curr = 0;
		//int start = 0;
		int stop = 0;
		for(int i = 0; i<knot.length;i++){
			knot[i] = i;
		}
		for (int len : lens ) {
			int start = curr;
			stop = start + len-1;
			while(start < stop) {
				//System.out.println("start: "+start);
				//System.out.println("stop: "+stop);
				int tmp = knot[start % knot.length];
				knot[start % knot.length] = knot[stop % knot.length];
				knot[stop % knot.length] = tmp;
				stop--;
				start++;
			}
			curr = curr + len + skipSize; //% lens.length; //% lens.length;
			skipSize++;
			System.out.println("---------");
			for (int i : knot) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println("-----");
	 	for (int i : knot) {
			System.out.print(i + " ");
		}
		System.out.println("-----");
		int a = knot[0]*knot[1];
		System.out.println("Part A: "+a);
	}
}
