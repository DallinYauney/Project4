package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import cards.Card;
import cards.GoBackCard;
import cards.GoToJail;
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

    public Deck(String filePath, Board board) throws FileNotFoundException {
        discardPile = new ArrayList<>();
        Scanner scnr = new Scanner(new File(filePath));
        drawPile = new ArrayList<>();
        discardPile = new ArrayList<>();
        
        
        while (scnr.hasNextLine()) {
            String cardName = scnr.nextLine();
            
            if (cardName == "Get Out of Jail Free") 
            	drawPile.add(new JailCard());
            
            
             else if (cardName.equals("Advance to Boardwalk"))
            	 drawPile.add(new MoveToCard(cardName,"Boardwalk"));
            
             else if (cardName.equals("Advance to Go (Collect $200)"))
            	 drawPile.add(new MoveToCard(cardName, "Go"));
            
             else if (cardName.equals("Advance to Illinois Avenue. If you pass Go, collect $200"))
            	 drawPile.add(new MoveToCard(cardName,"Illinois Avenue"));

             else if (cardName.equals("Advance to St. Charles Place. If you pass Go, collect $200"))
            	 drawPile.add(new MoveToCard(cardName,"St. Charles Place"));
            
             else if (cardName.equals("Take a trip to Reading Railroad. If you pass Go, collect $200"))
            	 drawPile.add(new MoveToCard(cardName,"Reading Railroad"));
            
             else if (cardName.equals("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled"))
            	 drawPile.add(new NearestRailroad(cardName));
            
             else if (cardName.equals("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown."))
            	 drawPile.add(new NearestUtility(cardName));
            
             else if (cardName.equals("Go Back 3 Spaces"))
            	 drawPile.add(new GoBackCard(cardName));
            
             else if (cardName.equals("Go to Jail. Go directly to jail, do not pass Go, do not collect $200"))
            	 drawPile.add(new GoToJail(cardName,board));
            
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
