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
