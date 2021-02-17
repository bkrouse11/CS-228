package edu.iastate.cs228.hw1;

/**
 * @author Brandon Rouse
 * Subclass to parent class TownCell. This Reseller subclass holds the information for a reseller in the neighborhood.
 * This person provides lower-cost, lower-bandwidth internet to users off the grid. 
 * This cell is occupied by a reseller user.
 */

public class Reseller extends TownCell{
	
	/**
	 * Reseller constructor that makes a cell with the state of RESELLER
	 * @param p Town variable of current town
	 * @param r Variable holding the row of the cell
	 * @param c Variable holding the column of the cell
	 */

	public Reseller(Town p, int r, int c) {
		super(p, r, c);
	}
	
	/**
	 * Returns the state of the current cell. This cell returns RESELLER
	 */

	@Override
	public State who() {
		return State.RESELLER;
	}
	
	/**
	 * Returns a new TownCell with a new state depending on its neighbors
	 * @param tNew TownCell that is going to be changed
	 */

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		TownCell output = new Reseller(tNew, row, col);
		
		if(nCensus[CASUAL] <= 3 || nCensus[EMPTY] <= 3) {
			output = new Empty(tNew, row, col);
		}
		else if(has5Casual(nCensus)) {
			output = new Streamer(tNew, row, col);
		}

		return output;
	}
}
