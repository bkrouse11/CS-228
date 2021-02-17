package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Outage;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Brandon Rouse
 * JUnit test class for Outage subclass
 */

class OutageTest {
	
	/**
	 * Outage constructor test, making sure a new Outage TownCell returns state OUTAGE
	 */

	@Test
	void testOutage() {
		Town t = new Town(4,4);
		TownCell test = new Outage(t, 1, 2);
		assertTrue(test.who() == State.OUTAGE);
	}
	
	/**
	 * Outage who() method, making sure a TownCell on a grid will return state OUTAGE
	 */

	@Test
	void testWho() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[0][0];
		assertTrue(test.who() == State.OUTAGE);
	}
	
	/**
	 * Outage next() method, making sure a Outage TownCell updates to the correct TownCell
	 */
	
	@Test
	void testNext() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[0][0];
		test = test.next(t);
		assertTrue(test.who() == State.EMPTY);
	}

}
