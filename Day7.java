import java.util.*;
import java.io.*;

public class Day7 {


	public class Program {
		String name;
		int weight;
		List<Program> pgl = new LinkedList<Program>();


		public Program (String name) {
			this.name = name;

		}
	
		public void addProgram (Program program) {
			pgl.add(program);
		}

	}
 
	public void parseAndStore(String line,List<Program> pgl,HashMap hm) {
		
		String[] splitLine = line.split(" ");
		Program program = new Program(splitLine[0]);
		program.weight = Integer.parseInt(splitLine[1].substring(1,splitLine[1].length()-1));
		pgl.add(program);
		
		for(int i = 3; i<splitLine.length;i++){
			Program child = new Program(splitLine[i].split(",")[0]);
			program.addProgram(child);
			//System.out.println("added " + child.name + " to " + program.name); 
		}

		if (splitLine.length < 3) {
			hm.put(program.name,program.weight);
			//System.out.println(program.name + " is top");
		}
	}

	public Program findParent(Program program, List<Program> pgl) {
		
		for (Program p : pgl){
			for (Program pp : p.pgl) {
				if (pp.name.equals(program.name)) {
					return p;
				}
			}
		}
			
		return null;
	}

	public int checkWeight(Program p) {
		int w = p.weight;
		return 0;
	}

	public Program findProgram(String name, List<Program> pgl) {
		for (Program p : pgl) {
			if(p.name.equals(name)){
				return p;
			}
		}
		return null;
	}


	public Integer calcWeight(Program p,HashMap<String,Integer> hm,List<Program> pgl) {
		if(hm.get(p.name) != null) {
			return hm.get(p.name);
		}
		else {
			Integer acc = 0;
			//System.out.println("else " + p.name);
			//System.out.println(p.pgl.get(0).name);
			for (Program pp : p.pgl) {
				//System.out.println("for");
				//System.out.println(pp.name);
				acc = acc + calcWeight(findProgram(pp.name,pgl),hm,pgl);
			}
			acc = acc + findProgram(p.name,pgl).weight;
			System.out.println(p.name + " " + acc); 
			hm.put(p.name,acc);
			return acc;
		}
	}

	public String findUnBalName(Program p, HashMap hm, List<Program> pgList) {
			
			Integer a = 0;
			Integer b = 0;
			for (int i = 0; i<p.pgl.size();i++) {
				int nonEqs = 0;
				a = calcWeight(findProgram(p.pgl.get(i).name,pgList)
								,hm
								,pgList);
				//System.out.println(p.pgl.get(i).name + " has sum " + a);
				for (int ii = 0;ii<p.pgl.size();ii++) {
					b = calcWeight(findProgram(p.pgl.get(ii).name,pgList)
									,hm
									,pgList);
					//System.out.println(p.pgl.get(ii).name + " has sum " + b);	
					if (!(a.equals(b))) {
						nonEqs++;
					}
				}
				if (p.pgl.size() == 2 && nonEqs > 0){
					//unBalFound = true;
					return p.pgl.get(i).name;
					//res = a - b;
					//break;
				}
				else if (nonEqs > 1){
					//unBalFound = true;
					return p.pgl.get(i).name;
					//res = a - b;
					//break;					
				}
			}
			return null;
	}
	
	// until no unbal is found
	public String lookForBalanced(Program p,HashMap hm,List<Program> pgList) {
		String unBalName = findUnBalName(p,hm,pgList); 
		
		if (unBalName == null) {
			return p.name;
		}
		else {
			return lookForBalanced(findProgram(unBalName,pgList),hm,pgList); 
			
		}
	}

	public static void main(String[] args) {
		System.out.println("Day 7");
		
		Day7 day7 = new Day7();
		List<Program> pgList = new LinkedList<Program>();
		HashMap<String,Integer> hm = new HashMap();

		try (BufferedReader br = new BufferedReader(new FileReader("./"+args[0]))) {
			String line;
			while((line = br.readLine()) != null) {
				day7.parseAndStore(line,pgList,hm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		Program curr = pgList.get(0);
		boolean currHasParent = true;
		while(currHasParent){
			
			Program parent = day7.findParent(curr,pgList);
				
			if (parent != null) {
				curr = parent;		
			}
			else {
				currHasParent = false;
			}

		}
		System.out.println("Part A: "+curr.name);
		
		// when final weight is found add to hash map 
		// terminal pg has empty childlist	
		// dynamic programming-isch
		boolean unBalFound = false;
		String unBalName = "did not find";
		Integer a = 0;
		Integer b = 0;
		Integer res = 0;
		for (Program p : pgList) {

			unBalName = day7.findUnBalName(p,hm,pgList); 
			if (unBalName != null) {
				
				String x = day7.lookForBalanced(day7.findProgram(unBalName,pgList)
							,hm
							,pgList);	
				System.out.println("pre, answ: " +  x);
				Integer y = hm.get(day7.findParent( day7.findProgram(x,pgList),
											pgList)
									.pgl.get(0).name
								 );
				if (y == 0) {
					y = hm.get(day7.findParent( 
						day7.findProgram(x,pgList),pgList).pgl.get(1).name);
				}
				System.out.println(day7.findProgram(x,pgList).weight-(hm.get(x)-y));
				break;
			}
			
			
			// Search down in tree for first occurence.
			
				
			/*if(unBalFound) {
				System.out.println("Part B: "+unBalName);
				System.out.println("rest: " + res
						 	+ " weigth: " 
							+ day7.findProgram(unBalName,pgList).weight);
				break;
			}*/	

			/*for(Program pp : p.pgl ) {
										
			}*/


			/*if (c = day7.unBalancedChild(p) != null) {
				
				for(Program pp : p.pgl) {
					if (c.weight != )	
				}	
				System.out.println(c.name);
				break;	
			}*/
		}	
	}	
}
