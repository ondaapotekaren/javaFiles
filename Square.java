import java.util.*;

class Square {
	ArrayList<String> pixels;
	Square() {
		this.pixels = new ArrayList<String>();
	}
	
	void addRow(String row) {
		this.pixels.add(row);
	}

	@Override public boolean equals(Object aThat) {
		Square square = (Square) aThat;
		ArrayList<String> one = new ArrayList<String>(square.pixels);
		ArrayList<String> two = new ArrayList<String>(this.pixels);
		boolean equal = true;

		for(int i = 0 ; i<two.size();i++) {
			if (! one.get(i).equals(two.get(i))) {
				equal = false;
				break;		
			}
		}
		return equal;
	}
	@Override public int hashCode() {
		int hash = 1;
		for (String s : pixels) {
			hash = hash + s.hashCode();
		}
		return hash;
	}
	void printSquare() {
		for(String s : pixels) {
			System.out.println(s);
		}
	}

	static Square parseRule(String s) {
		String[] ss = s.split("/");
		Square sq = new Square();
		for (String row : ss ) {
			sq.addRow(row);
		}
		return sq;
	}
}
