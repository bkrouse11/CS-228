package Classwork;

import java.util.ArrayList;

public class Raw {
	
	public static void main(String args[]) {
		ArrayList<String> a = new ArrayList<>();
		a.add("Foo");
		a.add("Bar");
		a.add("Baz");
		
		ArrayList<Integer> b = new ArrayList<>();
		b.add(42);
		
		for(int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
			System.out.println(a.get(i).length());
		}
		
		for(int i = 0; i < b.size(); i++) {
			System.out.println(b.get(i));
		}
		
		System.out.println("ArrayList a's class is " + a.getClass());
		System.out.println("ArrayList b's class is " + b.getClass());
	}
	
	public <E> void foo(E e) {
		E foo = (E) bar;
		
		E[] a = (E []) new Object[10];
	}
	
	

}
