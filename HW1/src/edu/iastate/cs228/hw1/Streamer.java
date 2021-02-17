package edu.iastate.cs228.hw1;

/**
 * @author Brandon Rouse
 * Subclass to parent class TownCell. This Streamer subclass holds the information for a streamer user of the internet.
 * This person is an aspiring e-celebrity with an appetite for bandwidth.
 * This cell is occupied by a streamer user.
 */

public class Streamer extends TownCell{
	
	/**
	 * Streamer constructor that makes a cell with the state of STREAMER
	 * @param p Town variable of current town
	 * @param r Variable holding the row of the cell
	 * @param c Variable holding the column of the cell
	 */

	public Streamer(Town p, int r, int c) {
		super(p, r, c);
	}
	
	/**
	 * Returns the state of the current cell. This cell returns STREAMER
	 */

	@Override
	public State who() {
		return State.STREAMER;
	}
	
	/**
	 * Returns a new TownCell with a new state depending on its neighbors
	 * @param tNew TownCell that is going to be changed
	 */

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		TownCell output = new Streamer(tNew, row, col);
		
		if(resellOrOutage(nCensus)) {
			output = new Reseller(tNew, row, col);
		}
		else if(nCensus[RESELLER] > 0) {
			output = new Outage(tNew, row, col);
		}
		else if(nCensus[OUTAGE] > 0) {
			output = new Empty(tNew, row, col);
		}
		
		return output;
	}
}
