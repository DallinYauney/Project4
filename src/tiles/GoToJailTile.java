package tiles;

import main.Board;

/**
 * Group 1 Cory Neilsen, Spencer Peck, Benjamin Shaw, Dallin Yauney CS 2430-002
 * Project 4: Monopoly Simulation – Spring 2026
 * 
 * A tile that when a player arrives at, they are sent to jail
 * 
 * @author Spencer Peck
 */
public class GoToJailTile extends Tile {
	
    public GoToJailTile(String name) {
		super(name);
	}
    /**
     * called when the player lands on this tile
     * (useful for deck tiles and "Go To Jail")
     * @param player the player who landed on this tile
     */
    public void action(Board board) {
    	board.movePlayerTo(board.jail);
    }
}
