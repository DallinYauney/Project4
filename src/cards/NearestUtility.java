package cards;

import main.Board;
import main.Player;

/**
 * Group 1 Cory Neilsen, Spencer Peck, Benjamin Shaw, Dallin Yauney CS 2430-002
 * Project 4: Monopoly Simulation – Spring 2026
 *
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
