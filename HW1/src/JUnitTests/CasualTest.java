package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Casual;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Brandon Rouse
 * JUnit test class for Casual subclass
 */

class CasualTest {
	
	/**
	 * Casual constructor test, making sure a new Casual TownCell returns state CASUAL
	 */
	
	@Test
	void testCasual() {
		Town t = new Town(4,4);
		TownCell test = new Casual(t, 1, 2);
		assertTrue(test.who() == State.CASUAL);
	}
	
	/**
	 * Casual who() method, making sure a TownCell on a grid will return state CASUAL
	 */

	@Test
	void testWho() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[1][2];
		assertTrue(test.who() == State.CASUAL);
	}
	
	/**
	 * Casual next() method, making sure a Casual TownCell updates to the correct TownCell
	 */
	
	@Test
	void testNext() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[1][2];
		test = test.next(t);
		assertTrue(test.who() == State.OUTAGE);
	}
	

}
