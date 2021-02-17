package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 * @author Brandon Rouse
 */

public class QuickSorter extends Sorter{
	
	 /**
	   * Sorts the given {@code WordList} using the given {@code Comparator} using the QuickSort algorithm.
	   * The contents of the given list <b>are</b> modified.
	   * 
	   * @param toSort
	   *   the list to sort
	   * @param comp
	   *   the comparator to use to compare elements of the list
	   * @throws NullPointerException
	   *   if either of {@code toSort} or {@code comp} are {@code null}
	   */
	
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException{
	if(toSort == null || comp == null) {
		throw new NullPointerException();
	}
	
    quickSortRec(toSort, comp, 0, toSort.length() - 1);
  }
  
  /**
   * Recursive method of quicksort that quicksorts the list array
   * 
   * @param list
   * 	the list to sort
   * @param comp
   * 	the comparator to use to compare elements of the list
   * @param start
   * 	starting index of list array
   * @param end
   * 	ending index of list array
   */

  private void quickSortRec(WordList list, Comparator<String> comp, int start, int end){
    if(start >= end) {
    	return;
    }
    
    int p = partition(list, comp, start, end);
    quickSortRec(list, comp, start, p - 1);
    quickSortRec(list, comp, p + 1, end);
  }
  
  /**
   * 
   * @param list
   * 	the list to sort
   * @param comp
   * 	the comparator to use to compare elements of the list
   * @param start
   * 	starting index of list array
   * @param end
   * 	ending index of list array
   * @return
   * 	integer to use as index of pivot
   */

  private int partition(WordList list, Comparator<String> comp, int start, int end){
    int midpoint = start + (end - start) / 2;
    String pivot = list.get(midpoint);
    
    boolean done = false;
    while(!done) {
    	while(comp.compare(list.get(start), pivot) < 0) {
    		start++;
    	}
    	
    	while(comp.compare(pivot, list.get(end)) < 0) {
    		end--;
    	}
    	
    	if(start >= end) {
    		done = true;
    	}
    	else {
    		list.swap(start, end);
    		start++;
    		end--;
    	}
    }
    
    return end;
  }
}
  
 
/*
 * TESTing below
 
//class to test quick sort by first letter
  
class FirstLetterComparator implements Comparator<String>{
	public int compare(String lhs, String rhs) {
		return lhs.charAt(0) - rhs.charAt(0);
	}
}

	Static class to test quick sort by length

class LengthComparator implements Comparator<String>{
	public int compare(String lhs, String rhs) {
		return lhs.length() - rhs.length();
	}
}
	



public static void main(String args[]) {
	  String[] contents = new String[5];
	  contents[0] = "hello";
	  contents[1] = "Sam";
	  contents[2] = "Brandon";
	  contents[3] = "Guan";
	  contents[4] = "Anthony";
	  
	  for(String s : contents) {
		  System.out.println(s);
	  }
	  
	  QuickSorter q = new QuickSorter();
	  
	  WordList hello = new WordList(contents);

	  Comparator<String> comp = new FirstLetterComparator();
	  q.sort(hello, comp);
	  
	  for(int j = 0; j < hello.length(); j++) {
		  System.out.println(hello.get(j));
	  }
	 
	  /*
	  Comparator<String> comp = new LengthComparator();
	  i.sort(hello,comp);
	  for(int j = 0; j < hello.length(); j++) {
		  System.out.println(hello.get(j));
	  }
*/
	
