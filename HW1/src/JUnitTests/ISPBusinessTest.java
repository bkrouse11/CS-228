package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.ISPBusiness;

/**
 * @author Brandon Rouse
 * ISPBusiness JUnit test class
 */

class ISPBusinessTest {
	
	/**
	 * Test for updatePlain() method, making sure plain gets updated to correct state
	 */

	@Test
	void testUpdatePlain() {
		Town town = new Town(4,4);
		town.randomInit(10);
		town = ISPBusiness.updatePlain(town);
		String correct = "E E E E \nC C O E \nC O E O \nC E E E \n";
		assertEquals(town.toString(), correct);
	}
	
	/**
	 * Test for getProfit() method, making sure returns correct profit for a Town
	 */
	
	@Test
	void testProfit() {
		Town town = new Town(4,4);
		town.randomInit(10);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		int profit = ISPBusiness.getProfit(town);
		assertEquals(profit, 12);
	}

}
