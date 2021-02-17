package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Empty;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Brandon Rouse
 * JUnit test class for Empty subclass
 */

class EmptyTest {
	
	/**
	 * Empty constructor test, making sure a new Empty TownCell returns state EMPTY
	 */

	@Test
	void testEmpty() {
		Town t = new Town(4,4);
		TownCell test = new Empty(t, 1, 2);
		assertTrue(test.who() == State.EMPTY);
	}
	
	/**
	 * Empty who() method, making sure a TownCell on a grid will return state EMPTY
	 */

	@Test
	void testWho() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[1][0];
		assertTrue(test.who() == State.EMPTY);
	}
	
	/**
	 * Empty next() method, making sure a Empty TownCell updates to the correct TownCell
	 */
	
	@Test
	void testNext() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[1][0];
		test = test.next(t);
		assertTrue(test.who() == State.CASUAL);
	}
}
