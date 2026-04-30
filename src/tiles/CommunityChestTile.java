package tiles;

import main.Board;
import main.Board.GameDecks;

/**
 * When landed upon this tile draws a card from the community Deck
 * This could have been created with a parameter of which deck to draw from, but for eliminating ambiguity this class was created
 * @author Spencer
 */
public class CommunityChestTile extends Tile {
	
    public CommunityChestTile(String name) {
		super(name);
	}
    /**
     * called when the player lands on this tile
     * (useful for deck tiles and "Go To Jail")
     * @param player the player who landed on this tile
     */
    public void action(Board board) {
    	board.drawFromDeck(GameDecks.CommunityChest);
    }
}
