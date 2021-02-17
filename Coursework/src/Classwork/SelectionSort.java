package Classwork;

import java.util.Comparator;
import java.util.Random;

public class SelectionSort {
	public static <T extends Comparable<? super T>> void selectionSort(T[] a) {
		
		for(int i = 0; i < a.length - 1; i++) {
			int mini = i;
			for(int j = i + 1; j < a.length; j++) {
				if(a[j].compareTo(a[mini]) < 0) {
					mini = j;
				}
			}
			
			T tmp = a[i];
			a[i] = a[mini];
			a[mini] = tmp;
		}
	}
	
public static void selectionSort(String[] a, Comparable<String> c) {
		
		for(int i = 0; i < a.length - 1; i++) {
			int mini = i;
			for(int j = i + 1; j < a.length; j++) {
				if(c.compare(a[j], a[mini]) < 0) {
					mini = j;
				}
			}
			
			String tmp = a[i];
			a[i] = a[mini];
			a[mini] = tmp;
		}
	}

public static <T> void selectionSort(T[] a, Comparable<? super T> c) {
	
	for(int i = 0; i < a.length - 1; i++) {
		int mini = i;
		for(int j = i + 1; j < a.length; j++) {
			if(c.compare(a[j], a[mini]) < 0) {
				mini = j;
			}
		}
		
		T tmp = a[i];
		a[i] = a[mini];
		a[mini] = tmp;
	}
}

	
class LengthComparator implements Comparator<String>{
	public int compare(String lhs, String rhs) {
		return lhs.length() - rhs.length();
	}
}

class FirstLetterComparator implements Comparator<String>{
	public int compare(String lhs, String rhs) {
		return lhs.charAt(0) - rhs.charAt(0);
	}
}


}
