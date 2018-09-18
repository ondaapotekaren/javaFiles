import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/*
Parallel solution using parallelStream.
Maybe cool theory. Definitly stupid in practice.
*/


public class Day21 {

	public static void main(String[] args){
		ReadInputFile rif = new ReadInputFile("./"+args[0]);
		ArrayList<String> als = rif.getStringList();
		Square input = new Square();
		ArrayList<Pair<Square>> rules = new ArrayList<Pair<Square>>();
		
		for (String s : als ) {
			String[] ss = s.split(" => ");
			Square left = Square.parseRule(ss[0]);
			Square right = Square.parseRule(ss[1]);
			Pair<Square> p = new Pair<Square>(left,right);
			rules.add(p);
		}
		
		input.addRow(".#.");
		input.addRow("..#");
		input.addRow("###");
		
		final int iterations = 18;
		int[] answ = new int[iterations];
		for (int i = 0;i<iterations;i++) {
			List<Square> enhancedSquares = breakIntoParts(input).parallelStream()
				.map(square -> findMatchingRule(square,rules))		
				.collect(Collectors.toList());
			input = buildSquare(enhancedSquares);
			answ[i] = countOnPixels(input);
		}
	
		System.out.println("Part A "+answ[4]);
		System.out.println("Part B "+answ[17]);
	}

	public static ArrayList<Square> createVariations(Square square) {
		ArrayList<Square> variations = new ArrayList<Square>();
		for (int rotate = 0;rotate < 2;rotate++) {
			for (int tip = 0;tip<4;tip++) {
				variations.add(square);	
				square = rightTip(square);
			}
			square = rotateFaceward(square);
		}			
		return variations;
	}

	public static Square findMatchingRule(Square square, List<Pair<Square>> rules) {
		return createVariations(square).parallelStream()
			.map(sq -> rules.parallelStream()
					.filter(rule -> sq.equals(rule.left))
					.map(rule -> rule.right)
					.collect(Collectors.toList()))
			.flatMap(x -> x.stream())
			.collect(Collectors.toList()).get(0);
	}

	public static int countOnPixels (Square square) {
		ArrayList<String> pixels = square.pixels;
		int acc = 0;
		for(String s : pixels) {
			for (int i=0;i<s.length();i++) {
				if (s.charAt(i) == '#') {
					acc++;
				}
			}
		}
		return acc; 
	}


	public static Square rotateFaceward(Square square) {
		ArrayList<String> input = square.pixels;
		ArrayList<String> output = new ArrayList<String>();
		for (String s : input) {
			output.add(new StringBuilder(s).reverse().toString());
		}
		Square sq2 = new Square();
		sq2.pixels = output;  
		return sq2;
	}

	public static Square rightTip(Square square) {
		ArrayList<String> input = square.pixels;
		ArrayList<String> output = new ArrayList<String>();
		for (int i = 0;i<input.size();i++) {
			String res = "";
			for (int j = 0;j<input.size();j++) {
				res = input.get(j).substring(i,i+1) + res;
			}
			output.add(res);
		}
		Square sq2 = new Square(); 
		sq2.pixels = output;
		return sq2;
	}
	
	public static Square buildSquare(List<Square> squares) {
		Square square = new Square();
		int miniSize = squares.get(0).pixels.size();
		int rowSize = (int) Math.sqrt(squares.size());

		for (int i = 0; i < rowSize*miniSize ; i++ ) {
			square.pixels.add("");
		}
		for (int y = 0; y < rowSize ; y++ ) {
			for (int x = 0; x < rowSize ; x++) {
				for (int j = 0; j<miniSize;j++) {
					String row = squares.get(y*rowSize+x).pixels.get(j);
					String res = square.pixels.get(y*miniSize+j);
					square.pixels.set(y*miniSize+j,res + row); 
				}
			}
		}
		return square;
	}

	public static List<Square> breakIntoParts(Square square) { 
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
				squares.add(s2);
			}
		}
		return squares;
	}
}
