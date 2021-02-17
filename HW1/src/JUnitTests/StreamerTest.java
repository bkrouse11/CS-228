package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Streamer;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Brandon Rouse
 * JUnit test class for Streamer subclass
 */


class StreamerTest {
	
	/**
	 * Streamer constructor test, making sure a new Streamer TownCell returns state STREAMER
	 */

	@Test
	void testStreamer() {
		Town t = new Town(4,4);
		TownCell test = new Streamer(t, 1, 2);
		assertTrue(test.who() == State.STREAMER);
	}
	
	/**
	 * Streamer who() method, making sure a TownCell on a grid will return state STREAMER
	 */

	@Test
	void testWho() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[2][3];
		assertTrue(test.who() == State.STREAMER);
	}
	
	/**
	 * Streamer next() method, making sure a Streamer TownCell updates to the correct TownCell
	 */
	
	@Test
	void testNext() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell test = t.grid[2][3];
		test = test.next(t);
		assertTrue(test.who() == State.OUTAGE);
	}

}
