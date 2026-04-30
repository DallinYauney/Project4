package cards;

import main.Board;
import main.Player;

/**
 * the GoToJailCard represents the card that sends the user to jail
 * 
 * @author Spencer Peck
 */
public class GoToJail extends Card {
	Board board;
	public GoToJail(String name, Board board) {
		super(name);
		this.board= board;
	}
	
	/**
	 * action moves the player back x spaces
	 * 
	 * @parameter
	 */
	public void action(Player player) {
		player.position = board.jail;
	}
}
