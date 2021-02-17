package edu.iastate.cs228.hw1;

/**
 * @author Brandon Rouse
 * TownCell abstract class. Parent class to Outage, Casual, Empty, Reseller, and Streamer.
 */

public abstract class TownCell {
	
	/**
	 * Protected instance variable plain holding town plain
	 */
	
	protected Town plain;
	
	/**
	 * Protected instance variable holding row of cell
	 */
	
	protected int row;
	
	/**
	 * Protected instance variable holding column of cell
	 */
	
	protected int col;
	
	/**
	 * Constant for RESELLER
	 */
	
	protected static final int RESELLER = 0;
	
	/**
	 * Constant for EMPTY
	 */
	
	protected static final int EMPTY = 1;
	
	/**
	 * Constant for CASUAL
	 */
	
	protected static final int CASUAL = 2;
	
	/**
	 * Constant for OUTAGE
	 */
	
	protected static final int OUTAGE = 3;
	
	/**
	 * Constant for STREAMER
	 */
	
	protected static final int STREAMER = 4;
	
	/**
	 * Constant for NUM_CELL_TYPE
	 */
	
	public static final int NUM_CELL_TYPE = 5;
	
	/**
	 * Static array to take census
	 */
	
	public static final int[] nCensus = new int[NUM_CELL_TYPE];
	
	/**
	 * TownCell constructor making a town cell
	 * @param p Town plain cell is being constructed on
	 * @param r Row cell is being constructed on
	 * @param c Column cell is being constructed on
	 */

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Censuses all cell types in the 3 X 3 neighborhood
	 * Use who() method to get who is present in the 
	 * @param nCensus counts of all customers
	 */
	
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		for(int r = -1; r <= 1; r++) {
			if(row + r < 0 || row + r >= plain.grid.length) continue;
			for(int c = -1; c <= 1; c++) {
				if(col + c < 0 || col + c >= plain.grid[0].length || (c == 0 && r == 0)) continue;
				switch(plain.grid[row + r][col + c].who()) {
					case CASUAL:
						nCensus[CASUAL]++;
						break;
					case STREAMER:
						nCensus[STREAMER]++;
						break;
					case RESELLER:
						nCensus[RESELLER]++;
						break;
					case EMPTY:
						nCensus[EMPTY]++;
						break;
					case OUTAGE:
						nCensus[OUTAGE]++;
						break;
				}
			}
		}
		
//		System.out.println("Reseller: " + nCensus[RESELLER]);
//		System.out.println("Empty: " + nCensus[EMPTY]);
//		System.out.println("Casual: " + nCensus[CASUAL]);
//		System.out.println("Outage: " + nCensus[OUTAGE]);
//		System.out.println("Streamer: " + nCensus[STREAMER]);
	}

	/**
	 * Gets the identity of the cell.
	 * @return State of current cell
	 */
	
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * @param tNew: town of the next cycle
	 * @return TownCell Cell type of next cell
	 */
	
	public abstract TownCell next(Town tNew);
	
	/**
	 * Protected helper method that helps the next() method for each of the subclass
	 * Don't have to duplicate code for this rule every time
	 * If true, cell updates to Reseller
	 * @param nCensus counts all of the customers
	 * @return if the cell is not Reseller or Outage and has at most one empty or outage neighbor
	 */
	
	protected boolean resellOrOutage(int nCensus[]) {
		boolean result = false;
		if(plain.grid[row][col].who() != State.RESELLER && plain.grid[row][col].who() != State.OUTAGE) {
			if(nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * Protected helper method that helps the next() method for each of the subclass
	 * Don't have to duplicate code for this rule every time
	 * If true, cell updates to Streamer
	 * @param nCensus counts all of the customers
	 * @return if the cell has 5 or more casual neighbors
	 */
	
	protected boolean has5Casual(int nCensus[]) {
		return nCensus[CASUAL] >= 5;
	}
}
