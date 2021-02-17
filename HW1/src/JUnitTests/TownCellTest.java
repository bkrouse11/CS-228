package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Casual;
import edu.iastate.cs228.hw1.Empty;
import edu.iastate.cs228.hw1.Outage;
import edu.iastate.cs228.hw1.Reseller;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Streamer;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Brandon Rouse
 * TownCell JUnit test class
 */

class TownCellTest {

	/**
	 * TownCell constructor test when user enters row, column, and town
	 */
	
	@Test
	void testTownCell() {
		Town t = new Town(5,5);
		TownCell cell = new Casual(t,1,1);
		assertTrue(cell.who() == State.CASUAL);
	}
	
	/**
	 * Census method test, making sure census collects the correct amount of OUTAGE neighbors from grid
	 */
	
	@Test
	void testCensusOutage() {
		Town t = new Town(4,4);
		t.randomInit(10);
		int[] census = new int[5];
		t.grid[1][1].census(census);
		
		assertEquals(3, census[3]);
	}
	
	/**
	 * Census method test, making sure census collects the correct amount of STREAMER neighbors from grid
	 */
	
	@Test
	void testCensusStreamer() {
		Town t = new Town(4,4);
		t.randomInit(10);
		int[] census = new int[5];
		t.grid[1][1].census(census);
		
		assertEquals(1, census[4]);
	}
	
	/**
	 * Census method test, making sure census collects the correct amount of EMPTY neighbors from grid
	 */
	
	@Test
	void testCensusEmpty() {
		Town t = new Town(4,4);
		t.randomInit(10);
		int[] census = new int[5];
		t.grid[1][1].census(census);
		
		assertEquals(2, census[1]);
	}
	
	/**
	 * Census method test, making sure census collects the correct amount of CASUAL neighbors from grid
	 */
	
	@Test
	void testCensusCasual() {
		Town t = new Town(4,4);
		t.randomInit(10);
		int[] census = new int[5];
		t.grid[1][1].census(census);
		
		assertEquals(1, census[2]);
	}
	
	/**
	 * Census method test, making sure census collects the correct amount of RESELLER neighbors from grid
	 */
	
	@Test
	void testCensusReseller() {
		Town t = new Town(4,4);
		t.randomInit(10);
		int[] census = new int[5];
		t.grid[1][1].census(census);
		
		assertEquals(1, census[0]);
	}
	
	/**
	 * Testing who() static method to make sure it returns CASUAL on casual TownCell
	 */
	
	@Test
	void testWhoCasual() {
		Town t = new Town(4,4);
		TownCell cell = new Casual(t,1,1);
		assertTrue(cell.who() == State.CASUAL);
	}
	
	/**
	 * Testing who() static method to make sure it returns OUTAGE on outage TownCell
	 */
	
	@Test
	void testWhoOutage() {
		Town t = new Town(4,4);
		TownCell cell = new Outage(t,1,1);
		assertTrue(cell.who() == State.OUTAGE);
	}
	
	/**
	 * Testing who() static method to make sure it returns STREAMER on streamer TownCell
	 */
	
	@Test
	void testWhoStreamer() {
		Town t = new Town(4,4);
		TownCell cell = new Streamer(t,1,1);
		assertTrue(cell.who() == State.STREAMER);
	}
	
	/**
	 * Testing who() static method to make sure it returns EMPTY on empty TownCell
	 */
	
	@Test
	void testWhoEmpty() {
		Town t = new Town(4,4);
		TownCell cell = new Empty(t,1,1);
		assertTrue(cell.who() == State.EMPTY);
	}
	
	/**
	 * Testing who() static method to make sure it returns RESELLER on reseller TownCell
	 */
	
	@Test
	void testWhoReseller() {
		Town t = new Town(4,4);
		TownCell cell = new Reseller(t,1,1);
		assertTrue(cell.who() == State.RESELLER);
	}
	
	/**
	 * Testing next() static method to make sure CASUAL cell gets updated correctly
	 */
	
	@Test
	void testNextCasual() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell result = t.grid[1][2].next(t);
		assertTrue(result.who() == State.OUTAGE);
	}
	
	/**
	 * Testing next() static method to make sure OUTAGE cell gets updated correctly
	 */
	
	@Test
	void testNextOutage() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell result = t.grid[2][2].next(t);
		assertTrue(result.who() == State.EMPTY);
	}
	
	/**
	 * Testing next() static method to make sure STREAMER cell gets updated correctly
	 */
	
	@Test
	void testNextStreamer() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell result = t.grid[2][1].next(t);
		assertTrue(result.who() == State.OUTAGE);
	}
	
	/**
	 * Testing next() static method to make sure EMPTY cell gets updated correctly
	 */
	
	@Test
	void testNextEmpty() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell result = t.grid[1][1].next(t);
		assertTrue(result.who() == State.CASUAL);
	}
	
	/**
	 * Testing next() static method to make sure RESELLER cell gets updated correctly
	 */
	
	@Test
	void testNextReseller() {
		Town t = new Town(4,4);
		t.randomInit(10);
		TownCell result = t.grid[0][1].next(t);
		assertTrue(result.who() == State.EMPTY);
	}
}
