package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs insertion sort
 * to sort the list.
 * @author Brandon Rouse
 */

public class InsertionSorter extends Sorter{

	 /**
	   * Sorts the given {@code WordList} using the given {@code Comparator} using the InsertionSort algorithm.
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
	
	int n = toSort.length();
    for(int i = 1; i < n; i++) {
    	int j = i;
    	while(j > 0 && (comp.compare(toSort.get(j-1), toSort.get(j)) > 0)) {
    		toSort.swap(j-1,j);
    		j--;
    	}
    }
  }  
}
  

/*
 * TESTing below
  
   //Static class to test insertion sort by first letter in strings
    
static class FirstLetterComparator implements Comparator<String>{
	public int compare(String lhs, String rhs) {
		return lhs.charAt(0) - rhs.charAt(0);
	}
}
  
   //Static class to test insertion sort by length of strings
   
static class LengthComparator implements Comparator<String>{
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
	  
	  InsertionSorter i = new InsertionSorter();
	  
	  WordList hello = new WordList(contents);

	  
	  Comparator<String> comp = new FirstLetterComparator();
	  i.sort(hello, comp);
	  
	  for(int j = 0; j < hello.length(); j++) {
		  System.out.println(hello.get(j));
	  }
	 
	  
	  Comparator<String> comp = new LengthComparator();
	  i.sort(hello,comp);
	  for(int j = 0; j < hello.length(); j++) {
		  System.out.println(hello.get(j));
	  }
	  
    }
*/


