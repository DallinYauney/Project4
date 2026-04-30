package tiles;

import main.Board;
import main.Board.GameDecks;

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
