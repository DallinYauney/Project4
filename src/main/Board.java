package main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

import tiles.ChanceTile;
import tiles.CommunityChestTile;
import tiles.GoToJailTile;
import tiles.Tile;
import java.util.Scanner;

public class Board {
    public Tile jail;
    Tile go;
    private Deck chanceDeck;
    private Deck chestDeck;
    public enum GameDecks {Chance, CommunityChest};
    Player player;
    Random random; // likes to be made once & used many times
    int timesSped;

    public static void main(String[] args) {
    	buildBoardTiles();
        int[] roundLengths = {
            1_000,
            10_000,
            100_000,
            1_000_000,
        };

        // preemptively print the header row for the CSV output
        printTitles();

        // pays for jail escape
        for(int roundLength : roundLengths) {
            runBatch(true, roundLength);
        }

        // waits for jail
        for(int roundLength : roundLengths) {
            runBatch(false, roundLength);
        }
    }

    /**
     * run 10 simulations for a given setting
     * @param escapesJail whether the player should buy their way out of jail immediately
     * @param totalRounds total rounds of game to simulate
     */
    public static void runBatch(boolean escapesJail, int totalRounds) {
        for(int i=0; i<10; i++) {
            new Board(new Player(escapesJail), totalRounds);
        }
    }

    /**
     * Constructor for a board. Directly triggers the game that board controls.
     * @param player the CPU player playing on the board
     * @param totalRounds the number of rounds to go through
     */
    public Board(Player player, int totalRounds) {
        int roundNum = 1;
        this.player = player;
        random = new Random();
        buildBoardTiles();
        this.player.position = go;

        // TODO: initialize tiles & set this.jail
        // TODO: set player to be on "Go"

        while(roundNum <= totalRounds) {
            int[] dice = rollDice();
            takeTurn(dice);
            roundNum++;
        }

        printStats(player.escapesJailStrat, totalRounds);
    }

    /**
     * fill an array of size 2 with random integers 1-6
     * @return two random numbers 1-6
     */
    private int[] rollDice() {
    	int[] output = new int[2];
        for(int i=0; i<output.length; i++)
            output[i] = random.nextInt(1, 7); // upper bound is *exclusive*
        return output;
    }

    /**
     * the turn each player takes.
     * @param dice the dice that got rolled for the player
     */
    private void takeTurn(int[] dice) {
        // 1. ask player to move
        int distance = player.decideDistance(dice);
        movePlayerBy(distance);

        // 2. if speeding, send to jail
        if(player.checkSpeeding()) {
            movePlayerTo(jail);
            timesSped++;
        }

        // 3. do action of current tile
        player.position.action(this);

        // 4. increment tile counter
        player.position.setVisitCounter(player.position.getVisitCounter() + 1);
    }

    /**
     * intended for use by Board (jail particularly)
     * @param destination tile for player to be put on
     */
    public void movePlayerTo(Tile destination) {
        player.position = destination;
    }

    /**
     * intended for use by cards.
     * static because cards don't have a reference to the board.
     * 
     * NOTE: doesn't work for "Go to jail" since no tile advances there
     * @param tileName name of the target tile
     */
    public static void movePlayerTo(Player player, String tileName) {
        do {
            player.position = player.position.getNextTile();
        } while(player.position.getName() != tileName);
    }

    /**
     * player decides how many tiles it *wants* to move, but we're
     * the ones to move it
     * @param tiles
     */
    private void movePlayerBy(int tiles) {
        for(int i=0; i<tiles; i++) {
            player.position = player.position.getNextTile();
        }
    }

    /**
     * the header for the CSV output. Print the titles of each tile
     */
    private static void printTitles() {
        // create dummy board to get its tiles
        Board dummy = new Board(new Player(false), 0);

        // print extra column headers to identify each run
        System.out.printf("Strategy,Turns,TimesSped,");

        // TODO: loop through tiles, printing each name w/comma between
    }

    /**
     * once the game is done, print the counter
     * for each tile in a CSV format to terminal.
     */
    private void printStats(boolean escapesJail, int totalRounds) {
        String jailString = escapesJail ? "Escapes" : "Waits";
        System.out.printf("%s,%d,%s,", jailString, totalRounds, timesSped);

        // TODO: print the visitCounter of each tile w/comma between
    }
    
    /**
     * Reads in a text file and assigns the names within the text file to tiles.
     * Certain names that signify a tile requiring specific functionality are filtered for and assigned to related tile objects
     * By reading in a file the order of the tiles can be easily seen and managed in a separate document outside the codebase.
     * 
     * An oddity in the file is that a line with "--" is present. This functions as a breaker for this method so we can store relevant data without reading it in, such as the jail class which exists outside the normal loop
     * 
     * @author SpencerJPeck
     */
    private void buildBoardTiles() {
		try {
    	Scanner scnr = new Scanner(Paths.get("resources/masterTileList.txt")); //Create new scanner based around the master tile list document
    	
    	Tile prevTile = go; //Variables used later in the loop
    	Tile currentTile = prevTile;
    	
    	if(scnr.hasNextLine()) {// IMPORTANT: "GO" should always be listed in the file first so that it is constructed first as it functions as the head of the linked list
			go = new Tile(scnr.nextLine()); //Read in first line
			prevTile = go; // Done with this assignment moving the current tile to the previous tile
    		
    	}
    	
    	ReadTiles: while(scnr.hasNextLine()) { // Read Tile Names from file and create tile objects accordingly (Skips Jail and Go)
    		String input = scnr.nextLine();
    		switch(input) {
    			case "--": // End of file has been detected. Link tail tile to head tile
    				break ReadTiles; //Exit While Loop
    	
    				//This could be replaced with RegEx but for this project it should be fine
    			case "Community Chest1":
    			case "Community Chest2":
    			case "Community Chest3":
    				currentTile = new CommunityChestTile(input);
    				break;
    				
    				//This could be replaced with RegEx but for this project it should be fine
    			case "Chance1":
    			case "Chance2":
    			case "Chance3":
    				currentTile = new ChanceTile(input);
    				break;

    			case "Go To Jail":
    			currentTile = new GoToJailTile(input);
    				break;
    				
    			default:
        			currentTile = new Tile(input);
    		}
			currentTile.setPreviousTile(prevTile); // Point back to last made tile
			prevTile.setNextTile(currentTile); // Point from last made tile to this tile
			prevTile = currentTile; // Done with this assignment moving the current tile to the previous tile
    	}

		prevTile.setNextTile(go);//Finished building main tiles, Link tail tile with head tile
		
    	//Loop through tiles from head to create jail and point to the correct exit spot
    	currentTile = go;
    	while(currentTile.getNextTile() != go) { // End loop after arriving back at the start
    		if(currentTile.getName().equals("Just Visiting")) { // If the Current Tile is after just visiting 
    			jail = new Tile("Jail");
    			jail.setNextTile(currentTile.getNextTile());
    			break;
    		}
    		currentTile = currentTile.getNextTile(); //Continue Loop
    		
    	}
    	scnr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    /**
     * Used to link card and deck functionality to tiles.
     * Depending on the enum selected the board will draw from the specified deck
     * As tiles do not have direct references to players or decks this allows
     * for the tiles to indicate which decks they activate when a player lands on them
     * @param A value from the GameDeck enum
     * @author Spencer J Peck
     */
    public void drawFromDeck(GameDecks deck) {
    	try {
    		switch(deck) {
    		case GameDecks.Chance:
    			chanceDeck.draw(player);
    			break;
    		case GameDecks.CommunityChest:
    			chestDeck.draw(player);
    			break;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
