package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs merge sort
 * to sort the list.
 * @author Brandon Rouse
 */

public class MergeSorter extends Sorter{
  
	 /**
	   * Sorts the given {@code WordList} using the given {@code Comparator} using the MergeSort algorithm.
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
	  
	  sort(toSort, comp, 0, toSort.length() - 1);
  }
  
  /**
   * MergeSort recursive method that merges two subarrays of list array
   * 
   * @param list
   * 	the list to sort
   * @param comp
   * 	the comparator to use to compare elements of the list
   * @param start
   * 	starting index
   * @param end
   * 	ending index
   */

  private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end){
	  
	int midpoint = (start + end) / 2;
    int sizeLeft = midpoint - start + 1;
    int sizeRight = end - midpoint;
    
    String[] left = new String[sizeLeft];
    String[] right = new String[sizeRight];
    
    for(int i = 0; i < sizeLeft; ++i) {
    	left[i] = list.get(start + i);
    }
    
    for(int j = 0; j < sizeRight; ++j) {
    	right[j] = list.get(midpoint + 1 + j);
    }
    
    int i = 0;
    int j = 0;
    
    int index = start;
    while(i < sizeLeft && j < sizeRight) {
    	if(comp.compare(left[i], right[j]) <= 0) {
    		list.set(index, left[i]);
    		i++;
    	}
    	else {
    		list.set(index, right[j]);
    		j++;
    	}
    	index++;
    }
    
    while(i < sizeLeft) {
    	list.set(index, left[i]);
    	i++;
    	index++;
    }
    
    while(j < sizeRight) {
    	list.set(index, right[j]);
    	j++;
    	index++;
    }
  }

  /**
   * Sorts the list array using the mergeSortRec method
   * 
   * @param list
   * 	the list to sort
   * @param comp
   * 	the comparator used to compare elements of the list
   * @param start
   * 	starting index 
   * @param end
   * 	ending index
   */
  
  private void sort(WordList list, Comparator<String> comp, int start, int end) {
	  if(start < end) {
		  int middle = (start + end) / 2;
		  
		  sort(list, comp, start, middle);
		  sort(list, comp, middle + 1, end);
		  
		  mergeSortRec(list, comp, start, end);
	  }
  }
}
  
  
  /*
   * TESTing below
  
  //Static class to test quick sort by first letter
  
class FirstLetterComparator implements Comparator<String>{
	public int compare(String lhs, String rhs) {
		return lhs.charAt(0) - rhs.charAt(0);
	}
}

	//Static class to test quick sort by length

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
	  
	  MergeSorter m = new MergeSorter();
	  
	  WordList hello = new WordList(contents);

	  Comparator<String> comp = new LengthComparator();
	  m.sort(hello, comp);
	  
	  for(int j = 0; j < hello.length(); j++) {
		  System.out.println(hello.get(j));
	  }
}
*/
