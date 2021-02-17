package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *  @author Brandon Rouse
 *  Town class that constructs a Town from either a file or user input
 */

public class Town {
	
	/**
	 * Private instance variables that hold the length and width of the grid
	 */
	
	private int length, width;
	
	/**
	 * Public instance variable that holds the grid of the Town
	 */
	
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length length of town grid
	 * @param width width of town grid
	 */
	
	public Town(int length, int width) {
		grid = new TownCell[length][width];
		this.length = length;
		this.width = width;
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName Name of the file holding town grid
	 * @throws FileNotFoundException
	 */
	
	public Town(String inputFileName) throws FileNotFoundException {
		File file = new File(inputFileName);
		
		Scanner scan = new Scanner(file);
		
		this.length = scan.nextInt();
		this.width = scan.nextInt();
		grid = new TownCell[length][width];
		
		for(int r = 0; r < length; r++) {
			for(int column = 0; column < width; column ++) {
				char c = scan.next().charAt(0);
				switch(c) {
					case 'C':
						grid[r][column] = new Casual(this, r, column);
						break;
					case 'S':
						grid[r][column] = new Streamer(this, r, column);
						break;
					case 'R':
						grid[r][column] = new Reseller(this, r, column);
						break;
					case 'E':
						grid[r][column] = new Empty(this, r, column);
						break;
					case 'O':
						grid[r][column] = new Outage(this, r, column);
						break;
				}
			}
		}
		scan.close();
	}
	
	/**
	 * Accessor to get width of grid
	 * @return Width of the grid.
	 */
	
	public int getWidth() {
		return width;
	}
	
	/**
	 * Accessor to get length of grid
	 * @return Length of the grid
	 */
	
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 * @param seed Seed for random generator to keep iterations consistent
	 */
	
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		int randNumber = rand.nextInt(5); //nextInt can produce negative number, must have 5 so no negative
		//System.out.println(randNumber);
		for(int r = 0; r < length; r++) {
			for(int c = 0; c < width; c++) {
				switch(randNumber % 5) {
					case 0:
						grid[r][c] = new Reseller(this, r, c);
						randNumber = rand.nextInt(5);
						//System.out.println(randNumber);
						break;
					case 1:
						grid[r][c] = new Empty(this, r, c);
						randNumber = rand.nextInt(5);
						//System.out.println(randNumber);
						break;
					case 2:
						grid[r][c] = new Casual(this, r, c);
						randNumber = rand.nextInt(5);
						//System.out.println(randNumber);
						break;
					case 3:
						grid[r][c] = new Outage(this, r, c);
						randNumber = rand.nextInt(5);
						//System.out.println(randNumber);
						break;
					case 4:
						grid[r][c] = new Streamer(this, r, c);
						randNumber = rand.nextInt(5);
						//System.out.println(randNumber);
						break;
				}
			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 * @return Town grid as a string
	 */
	
	@Override
	public String toString() {
		String s = "";
		
		for(int r = 0; r < length; r++) {
			for(int c = 0; c < width; c++) {
				switch(grid[r][c].who()) {
					case CASUAL:
						s += ("C ");
						break;
					case EMPTY:
						s += ("E ");
						break;
					case OUTAGE:
						s += ("O ");
						break;
					case RESELLER:
						s += ("R ");
						break;
					case STREAMER:
						s += ("S ");
						break;
				}
			}
			s += ("\n");
		}
		return s;
	}
}
