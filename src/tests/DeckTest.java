package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import cards.Card;
import cards.GoBackCard;
import cards.GoToJail;
import cards.JailCard;
import cards.MoveToCard;
import cards.NearestRailroad;
import cards.NearestUtility;
import main.Board;
import main.Deck;
import main.Player;

/**
 * Group 1 Cory Neilsen, Spencer Peck, Benjamin Shaw, Dallin Yauney CS 2430-002
 * Project 4: Monopoly Simulation – Spring 2026
 *
 * @author Spencer Peck
 */
class DeckTest {

    @Test
    void initializesDeckCorrectly() throws FileNotFoundException {
        Board board = new Board(new Player(false), 0);

        Deck deck = new Deck("resources/testDeck.txt", board);

        assertNotNull(deck);
        assertNotNull(deck.drawPile);
        assertNotNull(deck.discardPile);
        assertEquals(0, deck.discardPile.size());
    }

    @Test
    void loadsCorrectNumberOfCards() throws FileNotFoundException {
        Board board = new Board(new Player(false), 0);

        Deck deck = new Deck("resources/testDeck.txt", board);

        assertEquals(7, deck.drawPile.size());
    }

    @Test
    void constructor_createsCorrectCardTypes() throws FileNotFoundException {
        Board board = new Board(new Player(false), 0);

        Deck deck = new Deck("resources/testDeck.txt", board);

        assertTrue(deck.drawPile.get(0) instanceof MoveToCard);
        assertTrue(deck.drawPile.get(1) instanceof JailCard);
        assertTrue(deck.drawPile.get(2) instanceof GoToJail);
        assertTrue(deck.drawPile.get(3) instanceof GoBackCard);
        assertTrue(deck.drawPile.get(4) instanceof NearestRailroad);
        assertTrue(deck.drawPile.get(5) instanceof NearestUtility);
        assertTrue(deck.drawPile.get(6) instanceof Card);
    }

}
