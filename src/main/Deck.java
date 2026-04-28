package main;

import java.util.ArrayList;
import java.util.Collections;

import cards.Card;

public class Deck {
    ArrayList<Card> drawPile;
    ArrayList<Card> discardPile;

    public Deck(ArrayList<Card> cards) {
        drawPile = cards;
        discardPile = new ArrayList<>();
        //TODO Change this constructor to accept a file path to read in a chance file or community chest file
        //TODO Create method to read in strings
        	//This is the new method pseduoCode
        	//Read in next line
        	//If matches a card text that is a get out of jail create jail card
        	//if matches card text that is a move to location create a new MoveToCard with an extra parameter which is destination
        	//if go to nearest railroad/utility create new nearestRailroad/Utillity Card
        	//If go back three tiles create new gobackCard
        	//If not match other requirements create new Card (aka no functionality required)
    }

    /**
     * draw the top card & activate it
     * @param player player who drew the card
     */
    public void draw(Player player) {
        tryShuffle();
        
        Card next = drawPile.removeLast();
        next.use(player, discardPile);
    }

    /**
     * if the draw pile is empty, shuffle the
     * discard deck into the draw pile
     */
    private void tryShuffle() {
        if(drawPile.isEmpty()) {
            drawPile.addAll(discardPile);
            discardPile.clear();
            Collections.shuffle(drawPile);
        }
    }
}
