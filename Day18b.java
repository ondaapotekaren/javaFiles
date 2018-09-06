import java.util.*;
import java.io.*;

class Program {
	String id;
	int i = 0;
	HashMap<String,Long> regs;
	ArrayList<Long> queue;
 	boolean wait;
	int sent = 0;

	Program (String id,Long p) {
		this.id = id;
		this.regs = new HashMap<String,Long>();
		regs.put("p",p);
		this.queue = new ArrayList<Long>();
		wait = false;
	}

	class Inst {
		String inst;
		String reg;
		Long x;
		Long y;

		Inst(String inst, String reg, Long x,Long y) {
			this.inst = inst;
			this.reg = reg;
			this.x = x;
			this.y = y;
		}
	}

	Inst fetch(ArrayList<String> insts) {
		
		// frst register can also be number.

		String[] sl = insts.get(i).split(" ");
		String name = sl[0];
		String reg = sl[1];
		Long x = null;
		//Long x = regs.get(reg);
		Long y = null;

		if (Character.isDigit(sl[1].charAt(sl[1].length()-1))) {
			x = new Long(Integer.parseInt(sl[1]));
		} else {
			x = regs.get(sl[1]);
			if (x == null) {
				x = new Long(0);
				regs.put(sl[1],x);
			}
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
		Inst inst = new Inst(name,reg,x,y);
		return inst;
	}

	void exec(Inst inst,Program peer) {
		Long v;	
		switch (inst.inst) {
			case "snd":
				peer.queue.add(inst.x);
				sent++;	
				break;
			case "set":
				regs.put(inst.reg,inst.y);
				break;
			case "add":
				regs.put(inst.reg,inst.x+inst.y);
				break;
			case "mul":
				regs.put(inst.reg,inst.x*inst.y);
				break;
			case "mod":
				v = inst.x % inst.y;
				regs.put(inst.reg, inst.x % inst.y);
				break;
			case "rcv":
				if (queue.isEmpty()) {
					i--;
					wait = true;
				} else {
					regs.put(inst.reg,queue.remove(0));
					wait = false;
				}
				break;
			case "jgz":
				if (inst.x > 0) {
					i--;
					long yy = (long) inst.y;	
					i = i + (int)yy;	
				}	
				break;			
			default:
				break;
		}
		i++;	
	}
}

public class Day18b {

	public static void main(String[] args) {
		ArrayList<String> insts = new ArrayList<String>();
		Program p0 = new Program("p0",new Long(0));
		Program p1 = new Program("p1",new Long(1));
	
		long a = 34;
		long b = -34;
	
		try(BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			while((line = br.readLine()) != null) {
				insts.add(line);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean term0 = false;
		boolean term1 = false;
		
		while (! (term0 && term1)) {

			if(p0.i > -1 && p0.i < insts.size()) {
				p0.exec(p0.fetch(insts),p1);
			} else {
				System.out.println("0 term");
				term0 = true;
			}
			if(p1.i > -1 && p1.i < insts.size()) {
				p1.exec(p1.fetch(insts),p0);
			} else {
				System.out.println("1 term");
				term1 = true;
			}
			if(p0.wait && p1.wait) {
				break;
			}
			//System.out.println(p1.sent);
		}
		
		System.out.println(p1.sent);
	}
}
