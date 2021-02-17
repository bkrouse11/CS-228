package edu.iastate.cs228.hw2;


import java.io.FileNotFoundException;
import java.util.Comparator;


/**
 * An class that compares various methods of sorting.
 * @author Brandon Rouse
 */

public class SorterFramework{
  
   /**
   * Loads data necessary to run the sorter statistics output, and runs it.
   * The only logic within this method should be that necessary to use the
   * given file names to create the {@link AlphabetComparator},
   * {@link WordList}, and sorters to use, and then using them to run the
   * sorter statistics output.
   * 
   * @param args
   *   an array expected to contain two arguments:
   *    - the name of a file containing the ordering to use to compare
   *      characters
   *    - the name of a file containing words containing only characters in the
   *      other file
   */
	
  public static void main(String[] args){
    // TODO check arguments

    Alphabet alphabet = null;
    AlphabetComparator comparator = null;
    WordList words = null;
    Sorter[] sorters = new Sorter[3];

    try {
		alphabet = new Alphabet(args[0]);
	} catch (NullPointerException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
    
    try {
		words = new WordList(args[1]);
	} catch (NullPointerException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
    
    sorters[0] = new InsertionSorter();
    sorters[1] = new MergeSorter();
    sorters[2] = new QuickSorter();
    
    comparator = new AlphabetComparator(alphabet);

    SorterFramework toRun = new SorterFramework(sorters, comparator, words, 1000000);
    toRun.run();
  }


  /**
   * The comparator to use for sorting.
   */
  
  private Comparator<String> comparator;

  /**
   * The words to sort.
   */
  
  private WordList words;

  /**
   * The array of sorters to use for sorting.
   */
  
  private Sorter[] sorters;

  /**
   * The total amount of words expected to be sorted by each sorter.
   */
  
  private int totalToSort;

  /**
   * Constructs and initializes the SorterFramework.
   * 
   * @param sorters
   *   the array of sorters to use for sorting
   * @param comparator
   *   the comparator to use for sorting
   * @param words
   *   the words to sort
   * @param totalToSort
   *   the total amount of words expected to be sorted by each sorter
   * @throws NullPointerException
   *   if any of {@code sorters}, {@code comparator}, {@code words}, or
   *   elements of {@code sorters} are {@code null}
   * @throws IllegalArgumentException
   *   if {@code totalToSort} is negative
   */
  
  public SorterFramework(Sorter[] sorters, Comparator<String> comparator, WordList words, int totalToSort)throws NullPointerException,IllegalArgumentException{
	if(comparator == null || words == null || sorters == null) {
		throw new NullPointerException();
	}
	
	for(int index = 0; index < sorters.length; index++) {
		if(sorters[index] == null) {
			throw new NullPointerException();
		}
	}
	
	if(totalToSort < 0) {
		throw new IllegalArgumentException();
	}
	
    this.sorters = new Sorter[sorters.length];
	for(int i = 0; i < sorters.length; i++) {
		this.sorters[i] = sorters[i];
	}
	
	this.comparator = comparator;
	
	this.words = words.clone();
	
	this.totalToSort = totalToSort;
  }

  /**
   * Runs all sorters using
   * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
   * sortWithStatistics()}, and then outputs the following information for each
   * sorter:
   *  - the name of the sorter
   *  - the length of the word list sorted each time
   *  - the total number of words sorted
   *  - the total time used to sort words
   *  - the average time to sort the word list
   *  - the number of elements sorted per second
   *  - the total number of comparisons performed
   */
  
  public void run(){
    for(int i = 0; i < sorters.length; i++) {
    	Sorter sorter = sorters[i];
    	sorter.sortWithStatistics(words, comparator, totalToSort);
    	double loopIterations = sorter.getTotalWordsSorted() / (double)words.length();
    	System.out.println("--------List of " + words.length() + " words with " + totalToSort + " totalToSort--------");
    	System.out.println(sorter.getName());
    	System.out.println("Length of word list: " + words.length() + " words");
    	System.out.println("Total words sorted: " + sorter.getTotalWordsSorted());
    	System.out.println("Total sorting time: " + sorter.getTotalSortingTime()/1000000.0 + " ms"); //convert nanoseconds to milliseconds
    	System.out.println("Average sorting time: " + ((sorter.getTotalSortingTime()/1000000)/loopIterations) + " ms/list");
    	System.out.println("Number of elements sorted per second:  " + (sorter.getTotalWordsSorted()/(sorter.getTotalSortingTime()/1000000000.0)) + " elements"); //converting nanoseconds to seconds
    	System.out.println("Total number of comparisons: " + sorter.getTotalComparisons() + " comparisons\n");
    }
  }
}
