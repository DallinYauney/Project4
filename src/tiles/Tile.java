package tiles;

import main.Player;

public class Tile {
    public String name;
    public int visitCounter;
    public Tile previous;
    public Tile next;
    public String nextTitle;

    public Tile(String name) {
        this.name = name;
        visitCounter = 0;
    }

    /**
     * not sure if this'll be useful.
     * 
     * helper function to set the order of two adjacent
     * cards relative to each other.
     * @param before the card that comes first
     * @param after the card that comes last
     */
    public static void setOrder(Tile before, Tile after) {
        before.next = after;
        after.previous = before;
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
