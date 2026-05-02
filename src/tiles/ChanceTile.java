package tiles;

import main.Board;
import main.Board.GameDecks;

/**
 * Group 1 Cory Neilsen, Spencer Peck, Benjamin Shaw, Dallin Yauney CS 2430-002
 * Project 4: Monopoly Simulation – Spring 2026
 *
 * When landed upon this tile draws a card from the Chance Deck
 * This could have been created with a parameter of which deck to draw from, but for eliminating ambiguity this class was created
 * 
 * @author Spencer Peck
 */
public class ChanceTile extends Tile {

    public ChanceTile(String name) {
		super(name);
	}
    /**
     * called when the player lands on this tile
     * (useful for deck tiles and "Go To Jail")
     * @param player the player who landed on this tile
     */
    public void action(Board board) {
    	board.drawFromDeck(GameDecks.Chance);
    }
}
