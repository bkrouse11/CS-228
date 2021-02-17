package Classwork;

import java.util.ArrayList;

public class SimpleStack {
	private ArrayList<Integer> list;
	
	public SimpleStack() {
		list = new ArrayList<>();
	}
	
	public boolean push(int i) {
		return list.add(i);
	}
	
	public int peek() {
		return list.get(list.size() - 1);
	}
	
	public int pop() {
		return list.remove(list.size() - 1);
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public int size() {
		return list.size();
	}

}
