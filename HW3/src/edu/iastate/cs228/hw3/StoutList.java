package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 * @author Brandon Rouse
 */

public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>{

  /**
   * Default number of elements that may be stored in each node.
   */
	
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  
  public StoutList(){
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  
  public StoutList(int nodeSize){
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  
  public StoutList(Node head, Node tail, int nodeSize, int size){
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size(){
    return size;
  }
  
  /**
   * Inserts newNode into list after current
   * @param current
   * @param newNode
   */
  
  private void link(Node current, Node newNode) {
	newNode.previous = current;
	newNode.next = current.next;
	current.next.previous = newNode;
	current.next = newNode;
  }
  
  /**
   * Removes current from the list
   * @param current node wanted to be removed
   */
  
  private void unlink(Node current) {
	current.previous.next = current.next;
	current.next.previous = current.previous;
  }
  
  @Override
  public boolean add(E item){
	  if(item == null) {
		  throw new NullPointerException();
	  }
	  
	  if(tail.previous == head) {
		  link(head, new Node());
	  }
	  
	  if(tail.previous.count >= nodeSize) {
		  link(tail.previous, new Node());
	  }
	  
	  tail.previous.addItem(item);
	  size++;
	  return true;
  }

  @Override
  public void add(int pos, E item) {
	  if(pos < 0) {
		  throw new IndexOutOfBoundsException();
	  }
	  
	  add(find(pos).node, pos, item);
  }
  
  /**
   * Helper method for add. Includes all of the rules for adding an element
   * @param n node where item will be added
   * @param offset position when item will be added
   * @param item item to be added
   * @return Node and position of where item was added
   */
  
  private NodeInfo add(Node n, int offset, E item) {
	  if(item == null) {
		  throw new NullPointerException();
	  }
	  
	  if(tail.previous == head) {
		  link(head, new Node());
		  return new NodeInfo(head.next, 0);
	  }
	  
	  else if(n.previous != head && n.previous.count < nodeSize) {
		  n.previous.addItem(item);
		  size++;
		  return new NodeInfo(n.previous, n.previous.count - 1);
	  }
	  
	  else if(n == tail && n.previous.count == nodeSize) {
		  link(tail, new Node());
		  tail.previous.addItem(item);
		  size++;
		  return new NodeInfo(tail.previous, tail.previous.count - 1);
	  }
	  
	  else if(n.count < nodeSize) {
		  n.addItem(offset, item);
		  size++;
		  return new NodeInfo(n, offset);
	  }
	  
	  else {
		  link(n, new Node());
		  int count = nodeSize / 2;
		  int index = nodeSize / 2;
		  while(count < nodeSize) { // most last M/2 elements of node into new successor
			  n.next.addItem(n.data[index]);
			  n.removeItem(index);
			  count++;
		  }
		  
		  if(offset <= nodeSize / 2) {
			  n.addItem(offset, item);
			  size++;
			  return new NodeInfo(n, offset);
		  }
		  
		  if(offset > nodeSize / 2) {
			  n.next.addItem(offset - nodeSize/2, item);
			  size++;
		  }
		  
		  return new NodeInfo(n.next, offset - nodeSize/2);
	  }
  }

  @Override
  public E remove(int pos) {
    Node n = find(pos).node;
    int offset = find(pos).offset;
    E tmp = null; //item removed, need to return
    if(n == tail.previous && n.count == 1) {
    	tmp = n.data[offset];
    	n.removeItem(offset);
    	unlink(n);
    	size--;
    }
    
    else if(n == tail.previous || n.count > nodeSize / 2) {
    	tmp = n.data[offset];
    	n.removeItem(offset);
    	size--;
    }
    
    else {
    	if(n.next.count > nodeSize / 2) { // minimerge
    		n.addItem(n.next.data[0]);
    		n.removeItem(offset);
    		n.next.removeItem(0);
    	}
    	
    	else if(n.next.count <= nodeSize / 2) { //full merge
    		int count = 0;
    		n.removeItem(offset);
    		while(count < n.next.count) {
    			n.addItem(n.next.data[count]);
    			n.next.removeItem(count);
    			count++;
    		}
    		
    		unlink(n.next);
    	}
    }
    
    return tmp;
  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  
  public void sort(){
	  E[] arr = (E[]) new Comparable[size];
	  int arrIndex = 0;
	  while(arrIndex < size) {
		  Node current = find(arrIndex).node;
		  int offset = find(arrIndex).offset;
		  arr[arrIndex] = current.data[offset];
		  arrIndex++;
	  }
	  
	  insertionSort(arr, new InsertionComparator());
	  
	  int sizeHolder = size; //keeps track of original size before deletion of nodes
	  while(head.next != tail) { //deletes all nodes
		  unlink(head.next);
	  }
	  
	  int nodeIndex = 0;
	  while(nodeIndex < sizeHolder) {
		  add(arr[nodeIndex]);
		  nodeIndex++;
	  }
  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  
  public void sortReverse(){
	  E[] arr = (E[]) new Comparable[size];
	  int arrIndex = 0;
	  
	  while(arrIndex < size) {
		  Node current = find(arrIndex).node;
		  int offset = find(arrIndex).offset;
		  arr[arrIndex] = current.data[offset];
		  arrIndex++;
	  }
	  
	  bubbleSort(arr);
	  
	  int sizeHolder = size; //keeps track of original size before deletion of nodes
	  while(head.next != tail) { //deletes all nodes
		  unlink(head.next);
	  }
	  
	  int nodeIndex = 0;
	  while(nodeIndex < sizeHolder) {
		  add(arr[nodeIndex]);
		  nodeIndex++;
	  }
  }
  
  @Override
  public Iterator<E> iterator(){
    return new StoutListIterator(0);
  }

  @Override
  public ListIterator<E> listIterator(){
    return new StoutListIterator(0);
  }

  @Override
  public ListIterator<E> listIterator(int index){
    return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  
  public String toStringInternal(){
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  
  public String toStringInternal(ListIterator<E> iter){
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      
      sb.append("]");
      return sb.toString();
  }

  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  
  private class Node{
   
	 /**
     * Array of actual data elements.
     */
   
    public E[] data = (E[]) new Comparable[nodeSize];  // Unchecked warning unavoidable.
    
    /**
     * Link to next node.
     */
    
    public Node next;
    
    /**
     * Link to previous node;
     */
    
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    
    void addItem(E item){
      if (count >= nodeSize){
        return;
      }
      
      data[count++] = item;
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    
    void addItem(int offset, E item){
      if (count >= nodeSize){
    	  return;
      }
      
      for (int i = count - 1; i >= offset; --i){
        data[i + 1] = data[i];
      }
      
      ++count;
      data[offset] = item;
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    
    void removeItem(int offset){
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i){
        data[i - 1] = data[i];
      }
      
      data[count - 1] = null;
      --count;
    }    
  }
  
  /**
   * Basic stout iterator. Iterates through nodes
   */
  
  private class StoutIterator implements Iterator<E>{
	  
	  /**
	   * Instance variable holding a cursor node to iterator through the list
	   */
	  
	  private Node cursor;
	  
	  /**
	   * Instance variable holding the index of the list
	   */
	  
	  private int offset;
	  
	  /**
	   * Creates new StoutIterator
	   */
	  
	  public StoutIterator() {
		  cursor = head.next;
		  offset = 0;
	  }
	  
	  @Override
	  public boolean hasNext() {
		  return cursor.next != null && cursor.data[offset] != null;
	  }
	  
	  @Override
	  public void remove() {
		  throw new UnsupportedOperationException();
	  }
	  
	  @Override
	  public E next() {
		  if(offset >= nodeSize) { //if Node cursor is done, go to next done
			  if(cursor.next == tail) {
				  return null;
			  }
			  cursor = cursor.next;
			  offset = 0; //reset count
		  }
		  		  
		  return cursor.data[offset++];
	  }
  }
  
  /**
   * Stout List Iterator that can iterate forward and backward in the nodes. 
   * Adds and removes elements based on the specifications of the assignment
   */
 
  private class StoutListIterator implements ListIterator<E>{
	
	/**
	 * Instance variable holding the cursor that iterates through the nodes
	 */
	
	private Node cursor;
	
	/**
	 * Instance variable that holds the offset inside each node
	 */
	
	private int offset;
	
	/**
	 * Instance variable holding the which direction the element should be removed
	 * 0 if none, 1 if previous, -1 if next
	 */
	
	private int direction;
	
	/**
	 * Instance variable holding the position inside the whole linked list 
	 */
	
	private int position;
	  
    /**
     * Default constructor, calls other constructor at position 0
     */
	  
    public StoutListIterator(){
    	this(0);
    }

    /**
     * Constructor sets cursor at current position in a node
     * @param pos position cursor wants to be set at
     */
    
    public StoutListIterator(int pos){
    	cursor = find(pos).node;
    	offset = find(pos).offset;
    	direction = 0;
    	position = pos;
    }

    @Override
    public boolean hasNext(){
    	return position < size;
    }

    @Override
    public E next(){
    	E data = cursor.data[offset];
    	direction = -1;
    	position++;
    	offset++;
    	if(offset == nodeSize) {
    		offset = 0;
    		if(cursor.next == tail) {
    			throw new NoSuchElementException();
    		}
    		else {
    			cursor = cursor.next;
    		}
    	}
    	
    	return data;
   }

    @Override
    public void remove(){
    	if(direction == 0) {
    		throw new IllegalStateException();
    	}
    	
    	StoutList.this.remove(position + direction);
    	direction = 0;
    	position--;
    }

	@Override
	public boolean hasPrevious() {
		return position >= 0;
	}

	@Override
	public E previous() {
		E data = cursor.data[offset - 1];
    	direction = 1; 
    	offset--;
    	position--;
    	if(offset == 0) {
    		offset = nodeSize;
    		if(cursor.previous == head) {
    			throw new NoSuchElementException();
    		}
    		else {
    			cursor = cursor.previous;
    		}
    	}
    	
    	return data;
	}

	@Override
	public int nextIndex() {
		if(position > size - 1) {
			throw new NoSuchElementException();
		}
		return position;
	}

	@Override
	public int previousIndex() {
		if(position < 1) {
			throw new NoSuchElementException();
		}
		
		return position - 1;
	}

	@Override
	public void set(E e) {
		if(e == null) {
			throw new NullPointerException();
		}
		
		if(direction == 0) {
			throw new IllegalStateException();
		}
		
		StoutList.this.remove(position + direction);
		StoutList.this.add(position + direction, e);
		direction = 0;	
	}

	@Override
	public void add(E e) {
		if(e == null) {
			throw new NullPointerException();
		}
		
		if(direction == 0) {
    		throw new IllegalStateException();
    	}
		
		StoutList.this.add(position + direction, e);
		direction = 0;
		position++;
		offset--;
	}	
  }
 
  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  
  private void insertionSort(E[] arr, Comparator<? super E> comp){
	  int n = arr.length;
	  E temp = null;
	  
	  for(int i = 0; i < n; ++i) {
		  int j = i;
		  while(j > 0 && comp.compare(arr[j-1], arr[j]) >= 0) {
			  temp = arr[j];
			  arr[j] = arr[j - 1];
			  arr[j - 1] = temp;
			  --j;
		  }
	  }
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  
  private void bubbleSort(E[] arr){
	  int n = arr.length;
	  for(int i = 0; i < n - 1; i++) {
		  for(int j = 0; j < n - i - 1; j++) {
			  if(arr[j].compareTo(arr[j + 1]) <= 0) {
				  E tmp = arr[j];
				  arr[j] = arr[j+1];
				  arr[j+1] = tmp;
			  }
		  }
	  }
  }
  
  /**
   * NodeInfo class can be used to return the node and offset position at a position
   */
  
  private class NodeInfo{
	  
	  /**
	   * Instance variable holding node at current position
	   */
	  
	  public Node node;
	  
	  /**
	   * Instance variable holding offset at the node at current position
	   */
	  
	  public int offset;
	  
	  /**
	   * Constuctor makes a new NodeInfo with the node and offset of node
	   * @param node node position is at
	   * @param offset offset at the node at position
	   */
	  
	  public NodeInfo(Node node, int offset) {
		  this.node = node;
		  this.offset = offset;
	  }
  }
  
  /**
   * Finds the node and offset for the given logical index
   * @param pos position where the node and offset should be found at
   * @return node and offset at position
   */
  
  private NodeInfo find(int pos) {
	  Node cursor = head;
	  int sum = 0;
	  while(cursor.next != null && sum <= pos) {
		  cursor = cursor.next;
		  sum += cursor.count;
	  }
	  
	  return new NodeInfo(cursor, pos - (sum - cursor.count));
  }
  
  /**
   * Class for the comparator used inside the insertion sort method
   */
  
  private class InsertionComparator implements Comparator<E>{
	  
	@Override
	public int compare(E o1, E o2) {
		return o1.compareTo(o2);
	}
  }
}