package edu.iastate.cs228.hw1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Brandon Rouse
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 */

public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		
		for(int r = 0; r < tOld.getLength(); r++) {
			for(int c = 0; c < tOld.getWidth(); c++) {
				tNew.grid[r][c] = tOld.grid[r][c].next(tNew);
			}
		}
		
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town Town that profit is being calculated on 
	 * @return Profit for the current state in the town grid
	 */
	
	public static int getProfit(Town town) {
		int profit = 0;
		for(int r = 0; r < town.getLength(); r++) {
			for(int c = 0; c < town.getWidth(); c++) {
				if(town.grid[r][c].who() == State.CASUAL) {
					profit++;
				}
			}	
		}
		
		return profit;
	}
	

	/**
	 * Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should only print the integer part (Just print the 
	 *  integer value. Example if profit is 35.56%, your output should be just: 35).
	 *  
	 * Note that this method does not throws any exception, thus you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 */
	
	public static void main(String []args) {		
		Town town;
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Greetings fellow heathen. What would you like to do? \n 1. Insert a File \n 2. Start with a random seed");
			int choice = scan.nextInt();
			if(choice == 1) {
				System.out.println("Please enter your file containing grid");
				try {
					String fileName = scan.next();
					town = new Town(fileName);
					
					break;
				} catch (Exception e) {
					System.out.println("Not a valid file");
				}
			}
			else if(choice == 2){
				System.out.println("Please enter a random length");
				int length = scan.nextInt();
				System.out.println("Please enter a random width");
				int width = scan.nextInt();
				System.out.println("Please enter a random seed");
				int seed = scan.nextInt();
				
				
				town = new Town(length, width);
				town.randomInit(seed);
				
				break;
				
			}
			else {
				System.out.println("Invalid input. Enter either 1 or 2");
			}
		}
		
		double totalProfit = 0;
		//int iteration = 0;
		for(int cycle = 1; cycle <= 12; cycle++) {
			totalProfit += getProfit(town);
			//System.out.println(iteration + "\n" + town.toString());
			town = updatePlain(town);
			//iteration++;
			
			
		}
		
		int maxProfit = town.getLength() * town.getWidth() * 12;
		//System.out.println(maxProfit);
		//System.out.println(totalProfit);
		double profitPercentage = (totalProfit / maxProfit) * 100;
		
		System.out.println(profitPercentage);
	}
}
