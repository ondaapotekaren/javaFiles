import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Day14 {

	public void addAdjacent(int x,int y, int[][] mem){
		if(x > -1 && y > -1 && x < mem.length && y < mem[x].length && mem[x][y] == 1) {
			mem[x][y] = groupNmbr;
			addAdjacent(x,y+1,mem);
			addAdjacent(x-1,y,mem);
			addAdjacent(x,y-1,mem);
			addAdjacent(x+1,y,mem);
		}
	}

	static int groupNmbr = 1;

	public static void main(String[] args) {
		Day10 day10 = new Day10();
		int squares = 0;
		int[][] mem =  new int[128][128];
		for (int i = 0;i<128;i++) {
			String knot = day10.outputKnot("vbqugkhl-"+i);
			String res = "";
			for (int ii = 0;ii<knot.length();ii++) {
				BigInteger bi = new BigInteger(knot.substring(ii,ii+1),16);
				String binary = bi.toString(2);
				while (binary.length()<4) {
					binary = "0"+binary;
				}
				res = res + binary;
			}
			for (int ii = 0;ii<res.length();ii++) {
				if (res.charAt(ii) == '1'){
					squares++;
					mem[i][ii] = 1;
				}
			}
		}
		System.out.println("Part A: " + squares);
		
		Day14 day14 = new Day14();
		for (int x = 0;x<mem.length;x++){
			for(int y = 0; y<mem[x].length;y++){
				if(mem[x][y] == 1) {
					groupNmbr++;
					day14.addAdjacent(x,y,mem);
				}
			}		
		}
		System.out.println("Part B: "+(groupNmbr-1));
	}
}
