import java.util.*;
import java.io.*;

class Program {
	int i;
	HashMap<String,Long> regs;
	Program(int i){
		this.i = i;
		this.regs = new HashMap<String,Long>();
	}
}

public class Day18 {

	public static void main(String[] args) {
		HashMap<String,Long> regs = new HashMap<String,Long>();
		ArrayList<Long> sounds = new ArrayList<Long>();
		ArrayList<String> insts = new ArrayList<String>();
		Program program0 = new Program(0);
		
		try(BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			while((line = br.readLine()) != null) {
				insts.add(line);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		int i = 0;
		boolean frst = false;
			
		while ( i>=0 && i<insts.size()) {	
			
			String[] sl = insts.get(i).split(" ");
			String name = sl[0];
			Long x = regs.get(sl[1]);
			Long y = null;
			String reg = sl[1];
			if (x == null) {
				x = new Long(0);
				regs.put(reg,x);
			}
			if(sl.length > 2) {
				if (Character.isDigit(sl[2].charAt(sl[2].length()-1) )) {
					y = new Long(Integer.parseInt(sl[2]));
				}
				else {
					y=regs.get(sl[2]);
					if(y == null) { 
						y = new Long(0);
						regs.put(sl[2],y);
					}
				}
			}
			i++;
			switch (name) {
				case "snd": 
					sounds.add(x);
					break;
				case "set":
					regs.put(reg,y);
					break;
				case "add":
					regs.put(reg,x+y);
					break;
				case "mul":
					regs.put(reg,x*y);
					break;
				case "mod":
					regs.put(reg, x % y);
					break;
				case "rcv":
					if (x != 0) {
						Long a = sounds.get(sounds.size()-1);
						System.out.println(a);
						frst = true;
					}
					break;
				case "jgz":
					if (x > 0) {
						i--;
						long yy = (long) y;	
						i = i + (int)yy;	
					}
					break;			
				default:
					break;	
			}
			//System.out.println(ins + ",reg "+sl[1]+", arg1: "+x+", arg2: "+y+", jump: "+i);
			if (frst) {
				break;
			}
		}
	}
}
