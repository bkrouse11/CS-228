package Classwork;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.NoSuchElementException;

/*
 * AbstractCollection<E> implements all of the methods of Collection,
 * except for size() and iterator().
 */

public class FirstCollection<E> extends AbstractCollection<E> {
	private static final int DEFAULT_SIZE = 10;
	private E[] data;
	private int size;
	
	public FirstCollection() {
		this(DEFAULT_SIZE);
	}
	
	pubic FirstCollection(int initialCapacity) {
		data = (E[]) new Object[initialCapacity];
		size = 0;
	}
	
	public boolean add(E item) {
		checkCapacity();
		data[size] = item;
		size++;
		
		return true;
	}
	
	private void checkCapacity() {
		if(size == data.length) {
			data = Arrays.copyOf(data, data.length * 2);
		}
	}
	
	@Override
	public Iterator<E> iterator(){
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<E>{
		private int cursor = 0;
		private boolean canRemove = false;
		
		@Override
		public E next() {
			if(cursor >= size) {
				throw new NoSuchElementException();
			}
			
			canRemove = true;
			return data[cursor++];
		}
		
		@Override
		public boolean hasNext() {
			return cursor < size;
		}
		
		@Override
		public void remove() {
			if(!canRemove) {
				throw new IllegalStateException();
			}
			
			for(int i = cursor; i < size; i++) {
				data[i - 1] = data[i];
			}
			
			data[size - 1] = null;
			
			size--;
			cursor--;
			
			canRemove = false;
		}
	}
}

















