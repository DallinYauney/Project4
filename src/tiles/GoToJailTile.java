package tiles;

import main.Board;

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
