package cards;

import main.Board;
import main.Player;

/**
 * Group 1 Cory Neilsen, Spencer Peck, Benjamin Shaw, Dallin Yauney CS 2430-002
 * Project 4: Monopoly Simulation – Spring 2026
 *
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
		Board.movePlayerToNextRailroad(player);
	}
}
