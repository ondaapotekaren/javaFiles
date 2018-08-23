import java.util.*;
import java.util.function.*;
import java.util.stream.Stream; ////; //problem with java version :(

public class Person {
	
	public enum Sex {
		MALE,FEMALE 
	}
	Sex gender;
	private int age;
	public String name;
	
	public int getAge() {
		return age;
	}

	public Sex getGender(){
		return gender;
	}

	public String getName(){
		return name;	
	}
		
	public void printPerson(){
		System.out.println(name);
	}		
	
	public static void printPersonsWithPredicate(
		List<Person> roster, Predicate<Person> tester) {
		for (Person p : roster) {
			if (tester.test(p)){
				p.printPerson();
			}
		}
	}

	public static <X, Y > void processElements(
		Iterable<X> source,
		Predicate<X> tester,
		Function<X, Y> mapper,
		Consumer<Y> block) {
			for (X p : source) {
				if (tester.test(p)) {
					Y data = mapper.apply(p);
					block.accept(data);
				}
			}
		}

	public Person(String name,Sex gender,int age){
		this.gender = gender;
		this.age = age;
		this.name = name;
	}
	
	public static void main(String[] args){

		Person adam = new Person("Adam",Sex.MALE,25);
		Person eva = new Person("Eva",Sex.FEMALE,23);	
		Person maria = new Person("Maria",Sex.FEMALE,45);
		List<Person> listOffP = new LinkedList<Person>();

		listOffP.add(adam);	
		listOffP.add(eva);	
		listOffP.add(maria);
		/*	
		printPersonsWithPredicate(
		listOffP,
		p -> p.getGender() == Person.Sex.MALE
			&& p.getAge() >= 18
			&& p.getAge() <= 30
		);*/
	/*
		processElements(
			listOffP,
			p -> p.getGender() == Person.Sex.FEMALE
				&& p.getAge() <= 50,
			p -> p.getName(),
			name -> System.out.println(name)
		);

		listOffP
			.stream()
			.filter(
				p -> p.getGender() == Person.Sex.MALE
					&& p.getAge() >= 18
					&& p.getAge() <= 25)
			.map(p->p.getName())
			.forEach(name -> System.out.println(name));

	*/
		List<Integer> intList = new LinkedList<Integer>();
	
		for (int i = 1;i<1001;i++){
			intList.add(i); 
		}

		intList
			.parallelStream()
			.filter(x -> x * x > 100 && x * x < 20000)
			.map(x -> x + 10000)
			.forEach(x -> System.out.println(x));
	}
}
