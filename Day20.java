import java.util.*;
import java.util.function.*;
import java.util.stream.*;
public class Day20 {
	public static void main(String[] args) {
		
		ReadInputFile  rif = new ReadInputFile("./"+args[0]);
		ArrayList<String> als = rif.getStringList();
		ArrayList<ThreeDimCoord> accList = new ArrayList<ThreeDimCoord>();
		ArrayList<ThreeDimCoord> velList = new ArrayList<ThreeDimCoord>();
		ArrayList<ThreeDimCoord> posList = new ArrayList<ThreeDimCoord>();
		ArrayList<Particle> particles = new ArrayList<Particle>();
		
		int id = 0;
		
		for(String line : als) {
			ThreeDimCoord posC = new ThreeDimCoord(parse(line,0)[0],parse(line,0)[1],parse(line,0)[2]);
			posList.add(posC);
			ThreeDimCoord velC = new ThreeDimCoord(parse(line,1)[0],parse(line,1)[1],parse(line,1)[2]);
			velList.add(velC);
			ThreeDimCoord accC = new ThreeDimCoord(parse(line,2)[0],parse(line,2)[1],parse(line,2)[2]);
			accList.add(accC);
			Particle particle = new Particle(id,posC,velC,accC);
			particles.add(particle);
			id++;
		}
		ArrayList<Particle> al = getMinParList(particles, 
							p -> calcAbs(p,
								par -> par.getAcc()));
		System.out.println("Part A: " +al.get(0).id);
		particles = tickAndRemove(particles);
		System.out.println("Part B: "+particles.size());
}

	public static ArrayList<Particle> filterOutSingles(ArrayList<Particle> particles,Function<Particle,ThreeDimCoord> identityCond) {
		return particles.stream()
			.filter(p -> particles.stream()
					.filter(x -> identityCond.apply(x).equals(identityCond.apply(p)))
					.count() < 2)
			.collect(ArrayList::new
				,ArrayList::add
				,ArrayList::addAll);
	}

	public static boolean allAllign(ArrayList<Particle> particles) {
		return IntStream.range(0,particles.size()-1)
			.mapToObj(obj -> new Integer(obj))
			.map(i -> alligns(particles,i,p->p.getVel().x) && alligns(particles,i,p->p.getAcc().x))
			.reduce(true,(a,b) -> a && b);
	}
	
	public static boolean alligns(ArrayList<Particle> particles,Integer index,Function<Particle,Integer> get) {
		return get.apply(particles.get(index)) 
			<= get.apply(particles.get(index+1));
	}
	
	public static ArrayList<Particle> tickAndRemove(ArrayList<Particle> particles) {
		PosXCompare pxc = new PosXCompare();
		ArrayList<Particle> sortedNonColliders = filterOutSingles(particles,p->p.getPos()).stream()
				.map(p -> Particle.tick(p))
				.sorted(pxc)
				.collect(ArrayList::new
					,ArrayList::add
					,ArrayList::addAll);
		return allAllign(sortedNonColliders) 
			? sortedNonColliders
			: tickAndRemove(sortedNonColliders);
	}


	
	public static void removeById(ArrayList<Particle> pl, Set<Integer> set) {
		for(Integer id : set) {
			int i = 0;
			for (Particle p : pl) {
				if(p.id == id) {
					break;
				}
				i++;
			}
			pl.remove(i);
		}
	}

	public static ArrayList<Particle> getMinParList(ArrayList<Particle> parList,Function<Particle,Integer> calc) {
		Integer min = parList.stream()
			.map(p -> calc.apply(p))
			.min(Integer::compare)
			.get();
		ArrayList<Particle> pl = parList.stream()
			.filter(p -> calc.apply(p).equals(min))
			.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		return pl;

	}

	public static Integer calcAbs(Particle p, Function<Particle,ThreeDimCoord> get){
		return Math.abs(get.apply(p).x) 
		+ Math.abs(get.apply(p).y)
		+ Math.abs(get.apply(p).z);
	} 
	
	public static int[] parse(String line,int i) {
		int[] val = new int[3];
		
		String[] ss = line.split(" ")[i].split(",");
		val[0] = (int)Integer.parseInt(ss[0].split("<")[1]);
		val[1] = (int)Integer.parseInt(ss[1]);
		val[2] = (int)Integer.parseInt(ss[2].split(">")[0]);
		return val;
	}
}

class ThreeDimCoord {
	int x;
	int y;
	int z;
	ThreeDimCoord (int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override public boolean equals(Object aThat) {
		ThreeDimCoord tdc = (ThreeDimCoord) aThat;
		return this.x == tdc.x 
			&& this.y == tdc.y 
			&& this.z == tdc.z;

	}
	@Override public int hashCode() {
		return x+y+z;
	}
}

class Particle {
	int id;
	ThreeDimCoord pos;	
	ThreeDimCoord vel;	
	ThreeDimCoord acc;
	
	Particle(int id,ThreeDimCoord pos,ThreeDimCoord vel,ThreeDimCoord acc) {
		this.id = id;
		this.pos = pos;
		this.vel = vel;
		this.acc = acc; 
	}
	static Particle tick (Particle p1){

		int velx = p1.acc.x + p1.vel.x;
		int vely = p1.acc.y + p1.vel.y;
		int velz = p1.acc.z + p1.vel.z;
		int posx = p1.pos.x + velx;
		int posy = p1.pos.y + vely;
		int posz = p1.pos.z + velz;
		
		ThreeDimCoord p = new ThreeDimCoord(posx,posy,posz);
		ThreeDimCoord v = new ThreeDimCoord(velx,vely,velz);
		Particle p2 = new Particle(p1.id,p,v,p1.acc);
		return p2; 
	}
	
	boolean equalPos(Particle p) {
		return this.pos.equals(p.pos);
	}
	ThreeDimCoord getPos() {
		return pos;
	}
	ThreeDimCoord getVel() {
		return vel;
	}
	ThreeDimCoord getAcc() {
		return acc;	
	}
}

class PosXCompare implements Comparator<Particle> {
	public int compare (Particle p1 ,Particle p2) {
		if (p1.pos.x < p2.pos.x) return -1;
		if (p1.pos.x > p2.pos.x) return 1;
		else return 0;		
	}
}

class AccXCompare implements Comparator<Particle> {
	public int compare (Particle p1 ,Particle p2) {
		if (p1.acc.x < p2.acc.x) return -1;
		if (p1.acc.x > p2.acc.x) return 1;
		else return 0;		
	}
}

