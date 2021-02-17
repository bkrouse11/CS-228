package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Reseller;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Brandon Rouse
 * JUnit test class for Reseller subclass
 */

class ResellerTest {
	
	/**
	 * Reseller constructor test, making sure a new Reseller TownCell returns state RESELLER
	 */

	@Test
	void testReseller() {
		Town t = new Town(4,4);
		TownCell test = new Reseller(t, 1, 2);
		assertTrue(test.who() == State.RESELLER);
	}
	
	/**
	 * Reseller who() method, making sure a TownCell on a grid will return state RESELLER
	 */

	@Test
	void testWho() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[3][3];
		assertTrue(test.who() == State.RESELLER);
	}
	
	/**
	 * Reseller next() method, making sure a Reseller TownCell updates to the correct TownCell
	 */
	
	@Test
	void testNext() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[3][3];
		test = test.next(t);
		assertTrue(test.who() == State.EMPTY);
	}

}
