package cards;

import java.util.ArrayList;

import main.Player;

public class JailCard extends Card {
    private ArrayList<Card> destination;

    public JailCard() {
        super("Go to jail.");
    }

    /**
     * function called when the card is drawn from the deck
     * @param player the player who drew the card
     * @param destination the discard pile to which the card will be eventually returned
     */
    public void use(Player player, ArrayList<Card> destination) {
        player.addCard(this);
        this.destination = destination;
    }

    /**
     * called when the card is used by player.
     * returns self to the appropriate discard pile.
     */
    public void discard() {
        destination.add(this);
    }
}
