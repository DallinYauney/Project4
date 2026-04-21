package main;

import java.util.Random;

import tiles.Tile;

public class Board {
    Tile jail;
    Player player;
    Random random; // likes to be made once & used many times
    int timesSped;

    public static void main(String[] args) {
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
        player.position.action(player);

        // 4. increment tile counter
        player.position.visitCounter++;
    }

    /**
     * intended for use by Board (jail particularly)
     * @param destination tile for player to be put on
     */
    private void movePlayerTo(Tile destination) {
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
            player.position = player.position.next;
        } while(player.position.name != tileName);
    }

    /**
     * player decides how many tiles it *wants* to move, but we're
     * the ones to move it
     * @param tiles
     */
    private void movePlayerBy(int tiles) {
        for(int i=0; i<tiles; i++) {
            player.position = player.position.next;
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
}
