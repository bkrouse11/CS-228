package Classwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * Collection interface has add, size, contains, and iterator
 */

public class CollectionExample {
	
	public static void main(String args[]) {
		Collection<String> c = new ArrayList<String>();
		
		c.add("Phineas");
		c.add("Ferb");
		c.add("Isabella");
		c.add("Vanessa");
		
		Iterator<String> i = c.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
		for(String s : c) {
			System.out.println(s);
		}
	}

}
