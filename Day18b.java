import java.util.*;
import java.io.*;



public class Day18b {

	public static void main(String[] args) {
		ArrayList<String> insts = new ArrayList<String>();
		Program p0 = new Program();
		p0.regs.put("p",new Long(0));
		Program p1 = new Program();
		p1.regs.put("p",new Long(1));	
	
		ReadInputFile readInputFile = new ReadInputFile("./"+args[0]);
		for(String s : readInputFile.getStringList()){
			insts.add(s);
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
		}
		System.out.println(p1.sent);
	}
}
