import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Day21 {

	public static void main(String[] args){
		ReadInputFile rif = new ReadInputFile("./"+args[0]);
		ArrayList<String> als = rif.getStringList();
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<TransformationRule> transformations = new ArrayList<TransformationRule>();
		
		for (String s : als ) {
			String[] ss = s.split(" => ");
			TransformationRule tfr = new TransformationRule(ss[0],ss[1]);
			transformations.add(tfr);		
		}

		input.add(".#.");
		input.add("..#");
		input.add("###");

		// loop trough rules to find match
		// if no match is found flip the pattern
		// repeat until match is found
		
		// only need rightFlip
			
		// break into 2x2 or 3x3.
			
		ArrayList<String> out = rotateFaceward(input);
		System.out.println("-z-rotate-");
		for (String s : out ) {
			System.out.println(s);
		}
		System.out.println("tip by right");
		out = rTip(rTip(input));
		for (String s : out) {
			System.out.println(s);
		}
		System.out.println("-rightTip-");
		out = rTip(input);
		for (String s : out) {
			System.out.println(s);
		}
		System.out.println("-leftTip-by-right");
		out = rTip(rTip(rTip(input)));
		for (String s : out) {
			System.out.println(s);
		}
		
		Square s = new Square();
		/*		
		s.pixels.add("123456789");
		s.pixels.add("123456789");
		s.pixels.add("123456789");
		s.pixels.add("123456789");
		s.pixels.add("123456789");
		s.pixels.add("123456789");
		s.pixels.add("123456789");
		s.pixels.add("123456789");
		s.pixels.add("123456789");
		*/
		
		s.pixels.add("#..#");
		s.pixels.add("....");
		s.pixels.add("....");
		s.pixels.add("#..#");
		
		breakIntoParts(s);
		


	}

	public static ArrayList<String> rotateFaceward(ArrayList<String> input) {
		ArrayList<String> output = new ArrayList<String>();
		for (String s : input) {
			output.add(new StringBuilder(s).reverse().toString());
		} 
		return output;
	}
	public static ArrayList<String> rTip(ArrayList<String> input) {

		ArrayList<String> output = new ArrayList<String>();
		for (int i = 0;i<input.size();i++) {
			String res = "";
			for (int j = 0;j<input.size();j++) {
				res = input.get(j).substring(i,i+1) + res;
			}
			output.add(res);
		}
		return output;
	}
	
	public static Square buildSquare(ArrayList<Square> squares) {
		Square square = new Square();
		// must check size
		for(Square miniSquare : squares) {
			
	
		}
		return null;
	}

	public static ArrayList<Square> breakIntoParts(Square square) {
		
		ArrayList<Square> squares = new ArrayList<Square>();
		ArrayList<String> pixels = square.pixels;
		int size;

		if (pixels.size() % 2 == 0) {
			size = 2;
		} else {
			size = 3;
		}
		
		for (int y = 0;y<pixels.size();y=y+size) {
			for(int x = 0;x<pixels.get(0).length();x=x+size) {
				Square s2 = new Square();
				for (int i = y;i<y+size;i++) {
					String row = "";
					for(int j = x;j<x+size;j++) {
						row = row + pixels.get(i).substring(j,j+1);
					}
					s2.pixels.add(row);
				}
				s2.printSquare();
				squares.add(s2);
			}
		}
		return squares;
	}
}

class Square {
	ArrayList<String> pixels;
	
	Square() {
		this.pixels = new ArrayList<String>();
	}

	@Override public boolean equals(Object aThat) {
		Square square = (Square) aThat;
		ArrayList<String> one = new ArrayList<String>(square.pixels);
		ArrayList<String> two = new ArrayList<String>(pixels);
		Collections.sort(one);
		Collections.sort(two);
		return one.equals(two);
	}
	@Override public int hashCode() {
		int hash = 1;
		for (String s : pixels) {
			hash = hash + s.hashCode();
		}
		return hash;
	}
	void printSquare() {

		System.out.println("--Square--");
		for(String s : pixels) {
			System.out.println(s);
		}
	}

}


class TransformationRule {
	String left;
	String right;

	TransformationRule(String left,String right) {
		this.left = left;
		this.right = right;
	}
		
}



