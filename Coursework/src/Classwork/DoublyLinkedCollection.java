package Classwork;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedCollection<E> extends AbstractCollection<E> {
	private Node head = null;
	private Node pending = null;
	private int size = 0;
	
	private class Node{
		public E data;
		public Node next, prev;
		
		public Node(E data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	@Override
	public boolean add(E item) {
		Node tmp = new Node(item, head, null);
		
		if(head != null) {
			head.prev = tmp;
		}
		
		head = tmp;
		size++;
		
		return true;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Iterator<E> iterator(){
		return new LinkedIterator();
	}
	
	@Override
	public void remove() {
		if(pending == null) {
			throw new IllegalStateException();
		}
		
		if(pending.prev != null) {
			pending.prev.next = pending.next;
		}
		
		if(pending.next != null) {
			pending.next.prev = pending.prev;
		}
		
		if(head == pending) {
			head = pending.next;
		}
		
		pending = null;
		size--;
	}
	
	public class LinkedIterator implements Iterator<E> {
		private Node cursor;
		
		public LinkedIterator() {
			cursor = head;
		}
		
		@Override
		public boolean hasNext() {
			return cursor != null;
		}
		
		@Override
		public E next() {
			if(cursor == null) {
				throw new NoSuchElementException();
			}
			
			pending = cursor;
			cursor = cursor.next;
			return pending.data;
		}
	}
	

}
