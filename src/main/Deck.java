package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import cards.Card;
import cards.GoBackCard;
import cards.JailCard;
import cards.MoveToCard;
import cards.NearestRailroad;
import cards.NearestUtility;

/**
 * Deck is the basic deck that is in the Monopoly board game
 * it has the constructor that makes a deck using an ArrayList
 * 
 * @author Benjamin Shaw & Spencer Peck
 */
public class Deck {
    ArrayList<Card> drawPile;
    ArrayList<Card> discardPile;

    public Deck(String filePath) throws FileNotFoundException {
        discardPile = new ArrayList<>();
        Scanner scnr = new Scanner(new File(filePath));
        
        
        while (scnr.hasNextLine()) {
            String cardName = scnr.nextLine();
            
            if (cardName == "go to jail") 
            	drawPile.add(new JailCard());
            
             else if (cardName.equals("move to"))
            	 drawPile.add(new MoveToCard(cardName));
            
             else if (cardName.equals("go to the nearest railroad"))
            	 drawPile.add(new NearestRailroad(cardName));
            
             else if (cardName.equals("go to the nearest utility"))
            	 drawPile.add(new NearestUtility(cardName));
            
             else if (cardName.equals("go back three spaces"))
            	 drawPile.add(new GoBackCard(cardName));
            
             else
            	 drawPile.add(new Card(cardName));
            
            
        }
        
        scnr.close();
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
