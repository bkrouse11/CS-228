package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Town;

/**
 * @author Brandon Rouse
 * Town JUnit test class
 */

class TownTest {
	
	/**
	 * Town constructor test when user enters length and width
	 * Making sure length is being assigned correctly
	 */
	
	@Test
	void testTownLength() {
		Town t = new Town(8,9);
		assertEquals(t.getLength(), 8);
	}
	
	/**
	 * Town constructor test when user enters length and width
	 * Making sure width is being assigned correctly
	 */
	
	@Test
	void testTownWidth() {
		Town t = new Town(8,9);
		assertEquals(t.getWidth(), 9);
	}
	
	/**
	 * Town constructor test when user enters length and width
	 * Making sure grid is getting correct length
	 */
	
	@Test
	void testTownGridLength() {
		Town t = new Town(8,9);
		assertEquals(t.grid.length, 8);
	}
	
	/**
	 * Town constructor test when user enters length and width
	 * Making sure grid is getting correct width
	 */
	
	@Test
	void testTownGridWidth() {
		Town t = new Town(8,9);
		assertEquals(t.grid[0].length, 9);
	}
	
	/**
	 * Town constructor test when user enters a file
	 * Making sure grid is getting correct length
	 */
	
	@Test
	void testTownFileLength() {
		Town town = new Town(0,0);
		try {
			town = new Town("src/JUnitTests/ISP4x4.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		assertEquals(town.getLength(), 4);
	}
	
	/**
	 * Town constructor test when user enters a file
	 * Making sure grid is getting correct width
	 */
	
	@Test
	void testTownFileWidth() {
		Town town = new Town(0,0);
		try {
			town = new Town("src/JUnitTests/ISP4x4.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		assertEquals(town.getWidth(), 4);
	}
	
	/**
	 * Town constructor test when user enters a file
	 * Making sure grid is getting correct assignment
	 */
	
	@Test
	void testTownFileString() {
		Town town = new Town(0,0);
		try {
			town = new Town("src/JUnitTests/ISP4x4.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String correct = "O R O R \nE E C O \nE S O S \nE O R R \n";
		assertEquals(town.toString(), correct);
	}
	
	/**
	 * Town getWidth() method, making sure the accessor returns the correct value.
	 */
	
	@Test
	void testWidth() {
		Town t = new Town(8,9);
		assertEquals(t.getWidth(), 9);
	}
	
	/**
	 * Town getLength() method, making sure the accessor returns the correct value
	 */
	
	@Test
	void testLength() {
		Town t = new Town(8,9);
		assertEquals(t.getLength(), 8);
	}
	
	/**
	 * Town randomInit() method, making sure method creates the right grid with seed.
	 */
	
	@Test
	void testInit(){
		Town t = new Town(4,4);
		t.randomInit(10);
		String correct = "O R O R \nE E C O \nE S O S \nE O R R \n";
		assertEquals(t.toString(), correct);
	}
	
	/**
	 * Town toString() method, making sure method makes correct string with current grid.
	 */
	
	@Test
	void testString() {
		Town t = new Town(4,4);
		t.randomInit(10);
		String correct = "O R O R \nE E C O \nE S O S \nE O R R \n";
		assertEquals(t.toString(), correct);
	}
}
