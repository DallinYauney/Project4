package cards;

import main.Board;
import main.Player;

/**
 * NearestRailroad represents the next nearest railroad in the monopoly board
 * it has the action of moving the player to the nearest railroad
 * 
 * @author Benjamin Shaw
 */
public class NearestRailroad extends Card {

	public NearestRailroad(String name) {
		super(name);
	}
	
	/**
	 * moves the player to the nearest railroad
	 * 
	 * @parameter player
	 */
	public void action(Player player) {
		// TODO update this
		Board.movePlayerTo(player, name);
	}
}
