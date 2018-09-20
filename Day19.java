import java.util.*;

public class Day19 {
	
	public static void main(String[] args) {
		ReadInputFile rif = new ReadInputFile("./"+args[0]);
		ArrayList<String> diagram = rif.getStringList();
		Direction currentDir = Direction.SOUTH;
		int x=0;
		while(diagram.get(0).charAt(x) != '|') {
			x++;
		}
		int y = 1;
		Packet packet = new Packet(x,y,currentDir);
		String collected = "";
		ArrayList<Direction> surrDirs;
		int steps = 1;	
		do {
			String currentString = diagram.get(packet.y).substring(packet.x,packet.x+1);
			surrDirs = Packet.checkSurrounding(packet.x,packet.y,diagram);
			
			if (currentString.equals("+")) {
				for(int i = 0;i<surrDirs.size();i++) {
					if(! surrDirs.get(i).equals( Packet.opposite(currentDir) ) ) {
						currentDir = surrDirs.get(i); 
						break;
					}
				}		
				packet.move(currentDir);
			}
			else {
				if(! (currentString.equals("|") || currentString.equals("-"))) {
					collected = collected + currentString;
				}
				packet.move(currentDir);
			}

		steps++;

		} while (surrDirs.size() != 1);
		System.out.println("collected " +collected);
		System.out.println("steps "+steps);
	}
}

class Packet {
	int x;
	int y;
	Direction dir;

	Packet (int x, int y,Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public void move(Direction dir) {
		if(dir.equals(Direction.NORTH)) {
			this.y = y-1;
		} else if (dir.equals(Direction.EAST)) {
			this.x = x+1;
		} else if (dir.equals(Direction.SOUTH)) {
			this.y = y+1;
		} else if (dir.equals(Direction.WEST)) {
			this.x= x-1;
		}
	}
		
	public static ArrayList<Direction> checkSurrounding (Integer x,Integer y, ArrayList<String> diagram) {
		ArrayList<Direction> dirs = new ArrayList<Direction>();
		if (x > 0
			&& ! diagram.get(y).substring(x-1,x).equals(" ")) {
			dirs.add(Direction.WEST);
		}
		if (x < diagram.get(y).length() - 1
			&& ! diagram.get(y).substring(x+1,x+2).equals(" ")) {
			dirs.add(Direction.EAST);
		}
		if (y > 0 
			&& ! diagram.get(y-1).substring(x,x+1).equals(" ")) {
			dirs.add( Direction.NORTH);
		}
		if (y < diagram.size() - 1
			&& x < diagram.get(y+1).length() - 1 
			&& ! diagram.get(y+1).substring(x,x+1).equals(" ")) {
			dirs.add( Direction.SOUTH);
		}
		return dirs;
	}

	public static Direction opposite(Direction dir) {
		
		if(dir.equals(Direction.NORTH)) {
			return Direction.SOUTH;
		} else if (dir.equals(Direction.EAST)) {
			return Direction.WEST;
		} else if (dir.equals(Direction.SOUTH)) {
			return Direction.NORTH;
		} else if (dir.equals(Direction.WEST)) {
			return Direction.EAST;
		}
		return null;
	}
}


