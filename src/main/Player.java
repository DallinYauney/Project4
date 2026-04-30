package main;

import java.util.ArrayList;

import cards.JailCard;
import tiles.Tile;

public class Player {
    private ArrayList<JailCard> jailCards;
    boolean escapesJailStrat;
    Tile position;
    private int turnsInJail;
    int turnsSpeeding;

    public Player(boolean escapesJail) {
        escapesJailStrat = escapesJail;
        jailCards = new ArrayList<JailCard>();
        turnsInJail = 0;
    }

    /**
     * adds a Get Out of Jail Free card to the queue
     * @param card the specific card to be added
     */
    public void addCard(JailCard card) {
        jailCards.add(card);
    }

    /**
     * takes a turn of the game, to the extent that the player does anything.
     * @param dice array of the rolled dice for the turn
     * @return the number of squares the player would like to travel
     */
    public int decideDistance(int[] dice) {
        boolean doubles = (dice[0] == dice[1]);
        int distance = (dice[0] + dice[1]);
        if(position.getName() == "Jail") {
            boolean escapedJail = tryEscapeJail(doubles);
            if(escapedJail) {
                return distance;
            }
            
            // in jail but not escaped, don't move at all
            return 0;
        }

        // out of jail, so calc speeding & move normal distance
        incrementSpeeding(doubles);
        // System.out.printf("moving %s squares", distance);
        return distance;
    }

    /**
     * increments the speeding counter if currently speeding
     * @param doubles whether or not the current roll is a double
     */
    private void incrementSpeeding(boolean doubles) {
        if(doubles) {
            turnsSpeeding++;
        }
    }

    /**
     * returns whether the player has hit the speed limit.
     * If the player *is* speeding, reset the counter.
     * 
     * NOTE: THIS IS NOT A GETTER, IT CHANGES STATE.
     * @return if the player hit the speed limit
     */
    public boolean checkSpeeding() {
        if(turnsSpeeding >= 3) {
            turnsSpeeding = 0;
            return true;
        }
        return false;
    }

    /**
     * try to escape jail using the player's assigned strategy and the current dice roll.
     * @param doubles if the dice are doubles
     * @return whether the player ends up escaping jail
     */
    private boolean tryEscapeJail(boolean doubles) {
        // either strategy will try to use the getoutofjailfree card
        if(tryUseCard()) {
            return true;
        }

        if(escapesJailStrat) { // is the strategy to bail immediately?
            // "assume you paid the $50 and leave jail immediately"
            return true;
        } else {
            // doubles escapes jail for free
            if(doubles) return true;

            // "attempt to roll doubles for up to 3 turns"
            if(++turnsInJail < 3) {
                return false; // not 3 turns yet, keep up hope
            }

            return true; // abandon hope and pay bail
        }
    }

    /**
     * use a Get Out of Jail Free card, if one is available
     * @return whether or not a card was used
     */
    private boolean tryUseCard() {
        // is there a card to use?
        if(!jailCards.isEmpty()) {
            JailCard nextCard = jailCards.getFirst();
            nextCard.discard(); // send it back to the discard
            jailCards.removeFirst(); // remove it from *our* list
            return true;
        }

        return false;
    }

}
