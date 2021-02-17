package Classwork;

import java.util.AbstractSet;

public class BSTSet<E extends Comparable<? super E>> extends AbstractSet<E>{
	
	protected Node root;
	protected int size;
	
	protected class Node{
		public Node left;
		public Node right;
		public Node parent;
		public E data; 
		
		public Node(E key, Node parent) {
			data = key;
			this.parent = parent;
		}
		
		public boolean contains(Object o) {
			return findEntry((E) o) != null;
		}
	}
	
	protected Node findEntry(E key) {
		Node current = root;
		
		while(current != null) {
			int comp = current.data.compareTo(key);
			if(comp == 0) {
				return current;
			}
			
			else if (comp > 0) {
				current = current.left;
			}
			
			else { // comp < 0
				current = current.right;
			}
		}
		
		return null;
	}
	
	public boolean add(E key) {
		if(root == null) { //tree is empty
			root = new Node(key, null);
			size++;
			
			return true;
		}
		
		Node current = root;
		while(true) {
			int comp = current.data.compareTo(key);
			if(comp == 0) {
				return false; // won't insert a duplicate
			}
			
			else if(comp > 0) {
				if(current.left != null) { //keep going: find location to insert
					current = current.left;
				}
				
				else { //found location
					current.left = new Node(key, current);
					size++;
					return true;
				}
			}
			
			else {
				if(current.right != null) {
					current = current.right;
				}
				
				else {
					current.right = new Node(key, current);
					size++;
					return true;
				}
			}
		}
	}
	
	public boolean remove(Object obj) {
		Node n = findEntry((E) obj);
		if(n == null) {
			return false;
		}
		
		unlinkNode(n);
		
		return true;
	}

}
