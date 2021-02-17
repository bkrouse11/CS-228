package edu.iastate.cs228.hw1;

/**
 * @author Brandon Rouse
 * Subclass to parent class TownCell. This Casual subclass holds the information for a casual user of the internet.
 * This person checks their email 13 times a day. This cell is occupied by a casual user.
 */

public class Casual extends TownCell{
	
	/**
	 * Casual constructor that makes a cell with the state of CASUAL
	 * @param p Town variable of current town
	 * @param r Variable holding the row of the cell
	 * @param c Variable holding the column of the cell
	 */
	
	public Casual(Town p, int r, int c) {
		super(p,r,c);
	}
	
	/**
	 * Returns the state of the current cell. This cell returns CASUAL
	 */

	@Override
	public State who() {
		return State.CASUAL;
	}
	
	/**
	 * Returns a new TownCell with a new state depending on its neighbors
	 * @param tNew TownCell that is going to be changed
	 */

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		TownCell output = new Casual(tNew, row, col);
		
		if(resellOrOutage(nCensus)) {
			output = new Reseller(tNew, row, col);
		}
		else if(nCensus[RESELLER] > 0) {
			output = new Outage(tNew, row, col);
		}
		else if(nCensus[STREAMER] > 0) {
			output = new Streamer(tNew, row, col);
		}
		else if(has5Casual(nCensus)) {
			output = new Streamer(tNew, row, col);
		}
		
		return output;
	}
}
