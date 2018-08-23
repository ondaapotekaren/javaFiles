public class Box<T> {

	private T t;
	public void set(T t){
		this.t = t;
	}

	public T get(){
		return t;
	}

	public static void main(String[] args){
		
		Box<Integer> intBox = new Box<Integer>();	
		Box<String> stringBox = new Box<String>();
		stringBox.set("hello");
		intBox.set(23);
		//stringBox.set(24);
		System.out.println(stringBox.get());
		System.out.println(intBox.get());
		
	}
}
