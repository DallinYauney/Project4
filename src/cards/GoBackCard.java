package cards;

import main.Board;
import main.Player;

/**
 * the GoBackCard represents the card that is on the previous x spaces
 * 
 * @author Benjamin Shaw
 */
public class GoBackCard extends Card {
	
	public GoBackCard(String name) {
		super(name);
	}
	
	/**
	 * action moves the player back x spaces
	 * 
	 * @parameter
	 */
	public void action(Player player) {
		Board.movePlayerTo(player, name);
	}
}
