package tiles;

import main.Board;

/**
 * Group 1 Cory Neilsen, Spencer Peck, Benjamin Shaw, Dallin Yauney CS 2430-002
 * Project 4: Monopoly Simulation – Spring 2026
 * 
 * Serves as a space on a monopoly board. Contains some basic info about the tile and performs an action provided a board
 * 
 * @author Spencer Peck
 */
public class Tile {
    private String name;
    protected int visitCounter;
    private Tile previousTile;
    private Tile nextTile;
    private boolean isUtil;
    private boolean isRailroad;

    public Tile(String name) {
        this.name = name;
        visitCounter = 0;
        this.setIsUtil(false);
        this.setIsRailroad(false);
    }
    public Tile(String name, boolean util, boolean railRoad) {
        this.name = name;
        visitCounter = 0;
        this.setIsUtil(util);
        this.setIsRailroad(railRoad);
    }

    public int getVisitCounter() {
		return visitCounter;
	}

	public void setVisitCounter(int visitCounter) {
		this.visitCounter = visitCounter;
	}

	public Tile getPreviousTile() {
		return previousTile;
	}

	public void setPreviousTile(Tile previousTile) {
		this.previousTile = previousTile;
	}

	public Tile getNextTile() {
		return nextTile;
	}

	public void setNextTile(Tile nextTile) {
		this.nextTile = nextTile;
	}

	public String getName() {
		return name;
	}

	public boolean getIsUtil() {
		return isUtil;
	}

	public void setIsUtil(boolean isUtil) {
		this.isUtil = isUtil;
	}

	public boolean getIsRailroad() {
		return isRailroad;
	}

	public void setIsRailroad(boolean isRailroad) {
		this.isRailroad = isRailroad;
	}

	/**
     * called when the player lands on this tile
     * (useful for deck tiles and "Go To Jail")
     * @param player the player who landed on this tile
     */
    public void action(Board board) {
        // nothing by default
    }
}
