package edu.iastate.cs228.hw1;

/**
 * @author Brandon Rouse
 * Subclass to parent class TownCell. This Empty subclass holds the information for an empty grid cell.
 */

public class Empty extends TownCell{
	
	/**
	 * Empty constructor that makes a cell with the state of EMPTY
	 * @param p Town variable of current town
	 * @param r Variable holding the row of the cell
	 * @param c Variable holding the column of the cell
	 */

	public Empty(Town p, int r, int c) {
		super(p, r, c);
	}
	
	/**
	 * Returns the state of the current cell. This cell returns EMPTY
	 */

	@Override
	public State who() {
		return State.EMPTY;
	}
	
	/**
	 * Returns a new TownCell with a new state depending on its neighbors
	 * @param tNew TownCell that is going to be changed
	 */

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		TownCell output = new Empty(tNew, row, col);
		
		if(resellOrOutage(nCensus)) {
			output = new Reseller(tNew, row, col);
		}
		else {
			output = new Casual(tNew, row, col);
		}
		
		return output;
	}
}
