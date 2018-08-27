import java.util.*;
import java.io.*;

public class Day8 {

	public static Integer addOrInc(Integer x,Integer y,String string) {
		if (string.equals("dec")){
			return x - y;
		} else {
			return x + y;
		}
	}

	public static void main(String[] args) {
		System.out.println("Day 8");
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		int highest = -100000000; //unsafe	
		try (BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			while((line = br.readLine()) != null) {
				
				String[] sline = line.split(" ");
				String reg = sline[0];
				String op = sline[1];
				Integer val = Integer.parseInt(sline[2]);
				String condReg = sline[4];
				String cond = sline[5];
				Integer condVal = Integer.parseInt(sline[6]);

				Integer regVal = hm.get(reg);
				if (regVal == null) {
					regVal = 0;
				}
				 
				Integer condRegVal = hm.get(condReg);
				if (condRegVal == null) {
					condRegVal = 0;
				}
				if ((cond.equals("==") && (int)condRegVal == (int)condVal)
					|| (cond.equals("<") && (int)condRegVal < (int)condVal) 
					|| (cond.equals(">") && (int)condRegVal > (int)condVal) 
					|| (cond.equals("<=") && (int)condRegVal <= (int)condVal)
					|| (cond.equals(">=") && (int)condRegVal >= (int)condVal)
					|| (cond.equals("!=") && (int)condRegVal != (int)condVal)) {

					System.out.println("puts "+addOrInc(regVal,val,op)+" at "+reg);
					Integer x = new Integer(addOrInc(regVal,val,op));
					hm.put(reg,x);
					if (x > highest){
						highest = x;
					}
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set set = hm.entrySet();
		Iterator i = set.iterator();
		
		Map.Entry me = (Map.Entry)i.next();
		Integer val =(Integer) me.getValue();
		while(i.hasNext()) {
			me = (Map.Entry) i.next();
			Integer val2 = (Integer) me.getValue();
			if( (int) val2 > (int) val ) {
				val = val2;
			}
		}
		System.out.println("Part A: "+val);
		System.out.println("Part B: "+highest);	
	}
}
