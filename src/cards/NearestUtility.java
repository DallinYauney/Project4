package cards;

import main.Board;
import main.Player;

/**
 * NearestUtility represents the nearest utility on the monopoly board
 * 
 * @author Benjamin Shaw
 */
public class NearestUtility extends Card {
	
	public NearestUtility(String name) {
		super(name);
	}
	
	/**
	 * moves the player to the nearest utility
	 * 
	 * @parameter player
	 */
	public void action(Player player) {
		Board.movePlayerToNextUtil(player);
	}
}
