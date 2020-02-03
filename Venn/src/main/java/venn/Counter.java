package venn;

public class Counter {
	private int count;
	
	public Counter() {
		count = 0;
	}
	
	public int reset() {
		count=0;
		return count;
	}
	
	public int increment(int value) {
		count+=value;
		return count;
	}
	
	public int decrement(int value) {
		count-=value;
		return count;
	}
	
	public int increment() {
		count++;
		return count;
	}
	
	public int decrement() {
		count--;
		return count;
	}
	
	public String toString() {
		return "Counter: "+ count;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
