package edu.iastate.cs228.hw1;

/**
 * @author Brandon Rouse
 * Subclass to parent class TownCell. This Outage subclass holds the information for an outage grid cell.
 */

public class Outage extends TownCell{
	
	/**
	 * Outage constructor that makes a cell with the state of OUTAGE
	 * @param p Town variable of current town
	 * @param r Variable holding the row of the cell
	 * @param c Variable holding the column of the cell
	 */

	public Outage(Town p, int r, int c) {
		super(p, r, c);
	}
	
	/**
	 * Returns the state of the current cell. This cell returns OUTAGE
	 */

	@Override
	public State who() {
		return State.OUTAGE;
	}
	
	/**
	 * Returns a new TownCell with a new state depending on its neighbors
	 * @param tNew TownCell that is going to be changed
	 */

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		TownCell output = new Empty(tNew, row, col);
		
		return output;
	}
}
