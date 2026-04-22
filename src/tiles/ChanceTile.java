package tiles;

import main.Player;

public class ChanceTile extends Tile {
	
    public ChanceTile(String name) {
		super(name);
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
