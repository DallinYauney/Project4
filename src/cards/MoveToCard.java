package cards;

import main.Board;
import main.Player;

/**
 * MoveToCard is the move to card that is represented in the deck to move the player to a given
 * destination
 * 
 * @author Benjamin Shaw
 */
public class MoveToCard extends Card {
	
	public MoveToCard(String name) {
		super(name);
	}
	
	/**
	 * action moves the player to the destination that is given
	 * 
	 * @param player
	 * @param Destination
	 */
	public void action(Player player, String Destination) {
		Board.movePlayerTo(player, Destination);
	}
}
