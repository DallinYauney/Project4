package cards;

import main.Player;

/**
 * Group 1 Cory Neilsen, Spencer Peck, Benjamin Shaw, Dallin Yauney CS 2430-002
 * Project 4: Monopoly Simulation – Spring 2026
 *
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
		for(int i = 0; i < 3; i++) {
			player.position = player.position.getPreviousTile();
		}
	}
}
