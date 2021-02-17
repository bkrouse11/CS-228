package Classwork;

import java.util.AbstractSequentialList;

import javax.xml.soap.Node;

public class DoublyLinkedList<E> extends AbstractSequentialList<E> {
	
	private Node head,tail;
	private int size;
	
	public DoubleLinkedList() {
		head = new Node(null);
		tail = new Node(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	
	//Inserts newNode into list after current
	private void link(Node current, Node newNode) {
		newNode.prev = current;
		newNode.next = current.next;
		current.next.prev = newNode;
		current.next = newNode;
	}
	
	//Removes current from the list
	private void unlink(Node current) {
		current.prev.next = current.next;
		current.next.prev = current.prev;
	}
	
	//Returns the node at index pos
	private Node findNodeByIndex(int pos) {
		if(pos == -1) {
			return head;
		}
		if(pos == size) {
			return tail;
		}
		Node tmp = head.next;
		for(int count = 0; count < pos; count ++, tmp = tmp.next) {
		}
		
		return tmp;
	}
	
	public boolean add(E item) {
		link(tail.prev, new Node(tmp));
		size++;
		return true;
	}
	
	public void add(int pos, E item) {
		if(pos < 0 || pos > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node tmp = new Node(item);
		Node pred = findNodeByIndex(pos - 1);
		link(pred,tmp);
		size++;
	}
	
	public ListIterator<E> listIterator(){
		return new DoublyLinkedIterator();
	}
	
	public ListIterator<E> listIterator(int pos){
		return new DoublyLinkedIterator(pos);
	}
	
	private class DoublyLinkedIterator implements ListIterator<E>{
		
		private static final int BEHIND = -1;
		private static final int AHEAD = 1;
		private static final int NONE = 0;
		
		private Node cursor;
		private int direction;
		private int index;
		
		public DoublyLinkedIterator(int pos) {
			if(pos < 0 || pos > size) {
				throw new IndexOutOfBoundsException();
			}
			
			cursor = findNodeByIndex(pos);
			direction = NONE;
			index = pos;
		}
		
		public DoublyLinkedIterator() {
			this(0);
		}
		
		public int nextIndex() {
			return index;
		}
		
		public int previousIndex() {
			return index - 1;
		}
		
		public boolean hasNext() {
			return index < size;
		}
		
		public boolean hasPrevious() {
			return index > 0;
		}
		
		
	}
	
	private class Node{
		public E data;
		
		public Node(E data) {
			this.data = data;
		}
	}


}
