import java.util.*;
import java.io.*;

public class Day10 {

	public static void main(String[] args) {
		System.out.println("Day 10");
		String lens1 = "63,144,180,149,1,255,167,84,125,65,188,0,2,254,229,24";
		int[] lens = new int[lens1.length()+5];
		
		// convert to ASCII
		for(int i=0;i<lens1.length();i++){
			lens[i] = (int)lens1.charAt(i);	
		}

		lens[lens1.length()] = 17;
		lens[lens1.length()+1] = 31;
		lens[lens1.length()+2] = 73;
		lens[lens1.length()+3] = 47; 
		lens[lens1.length()+4] = 23;

		int[] knot = new int[256];
		int skipSize = 0;
		int curr = 0;
		for(int i = 0; i<knot.length;i++){
			knot[i] = i;
		}

		for (int i = 0; i<64;i++) {
			for (int len : lens ) {
				int start = curr;
				int stop = start + len-1;
				while(start < stop) {
					int tmp = knot[start % knot.length];
					knot[start % knot.length] = knot[stop % knot.length];
					knot[stop % knot.length] = tmp;
					stop--;
					start++;
				}
				curr = curr + len + skipSize; 
				skipSize++;
			}
			
		}
		// dense hash
		int[] dens = new int[16];
		int j = 0;
		for(int i = 0;i<knot.length;i=i+16){
			int xor = knot[i];
			for(int ii = 1;ii<16;ii++){
				xor = xor ^ knot[i+ii];
			}
			dens[j] = xor;
			j++;
		}

		// convert to hexa
		String hex = "";
		for(int i : dens){
			String conv = Integer.toHexString(i);
			if(conv.length() == 1){
				conv = "0"+conv;
			}
			hex = hex + conv;
		}
		System.out.println("Part B: "+hex);
	}
}
