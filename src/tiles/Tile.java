package tiles;

import main.Player;

public class Tile {
    private String name;
    protected int visitCounter;
    private Tile previousTile;
    private Tile nextTile;
    private String nextTitleName;

    public Tile(String name) {
        this.name = name;
        visitCounter = 0;
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

	/**
     * called when the player lands on this tile
     * (useful for deck tiles and "Go To Jail")
     * @param player the player who landed on this tile
     */
    public void action(Player player) {
        // nothing by default
    }
}
