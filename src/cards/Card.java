package cards;

import java.util.ArrayList;

import main.Player;

public class Card {
    String name;

    public Card(String name) {
        this.name = name;
    }

    /**
     * function called when the card is drawn from the deck
     * @param player the player who drew the card
     * @param destination the discard pile to which the card will be eventually returned
     */
    public void use(Player player, ArrayList<Card> destination) {
        // because not all cards get immediately discarded, it's up to
        // the card to put itself in the discard pile, as such
        destination.add(this);

        // some cards have special actions that should be taken
        action(player);
    }

    public void action(Player player) {
        // nothing by default, used by cards like "Go back 3"
    }
}
