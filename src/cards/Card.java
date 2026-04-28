package cards;

import java.util.ArrayList;

import main.Player;

public class Card {
    String name;
    //TODO Create cardclasses that extend Card
    	//MoveToCard.Action -> Call board.movepllayerto(Destination String)
    	//MoveTo will require a second string that is the destination string that needs to be filled in at construction
    
    	//JailCard. Action add this card to player.jailcards (Note that dallin is working on changing this functionality so it will be a different object type than current)
    	
    	//MoveToNearestRailroad.Action Loop player.position = next position until one of the railroad tiles are found
    
    	//MoveBack.acttion Player postion = positioin.prev do this x times.

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
