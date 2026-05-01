package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Board;
import main.Player;
import tiles.ChanceTile;
import tiles.CommunityChestTile;
import tiles.GoToJailTile;
import tiles.Tile;

class BoardTest {

    @Test
    void buildBoardTiles_initializesGoAndPlayerPosition() {
        Board board = new Board(new Player(false), 0);

        assertNotNull(board.go);
        assertEquals("Go", board.go.getName());
        assertSame(board.go, board.player.position);
    }

    @Test
    void buildBoardTiles_createsConnectedLinkedList() {
        Board board = new Board(new Player(false), 0);

        Tile current = board.go;
        int preventInfinteLoop = 0;
        boolean returnedToGo = false;

        do {
            assertNotNull(current.getNextTile(), "Each tile should have a next tile");
            current = current.getNextTile();
            preventInfinteLoop++;

            if (current == board.go) {
                returnedToGo = true;
                break;
            }
        } while (preventInfinteLoop < 100);

        assertTrue(returnedToGo, "Board should loop back to Go");
    }

    @Test
    void buildBoardTiles_createsJailTile() {
        Board board = new Board(new Player(false), 0);

        assertNotNull(board.jail);
        assertEquals("Jail", board.jail.getName());
        assertNotNull(board.jail.getNextTile());
    }

    @Test
    void buildBoardTiles_assignsSpecialTileTypes() {
        Board board = new Board(new Player(false), 0);

        Tile current = board.go;
        boolean foundChance = false;
        boolean foundChest = false;
        boolean foundGoToJail = false;

        do {
            if (current.getName().equals("Chance1")) {
                assertTrue(current instanceof ChanceTile);
                foundChance = true;
            }

            if (current.getName().equals("Community Chest1")) {
                assertTrue(current instanceof CommunityChestTile);
                foundChest = true;
            }

            if (current.getName().equals("Go To Jail")) {
                assertTrue(current instanceof GoToJailTile);
                foundGoToJail = true;
            }

            current = current.getNextTile();
        } while (current != board.go);

        assertTrue(foundChance, "Chance1 exists");
        assertTrue(foundChest, "Community Chest1 exists");
        assertTrue(foundGoToJail, "Go To Jail exists");
    }

    @Test
    void buildBoardTiles_marksUtilityAndRailroadTiles() {
        Board board = new Board(new Player(false), 0);

        Tile current = board.go;
        boolean foundUtility = false;
        boolean foundRailroad = false;

        do {
            if (current.getName().equals("Electric Company")) {
                assertTrue(current.getIsUtil());
                assertFalse(current.getIsRailroad());
                foundUtility = true;
            }

            if (current.getName().equals("Reading Railroad")) {
                assertTrue(current.getIsRailroad());
                assertFalse(current.getIsUtil());
                foundRailroad = true;
            }

            current = current.getNextTile();
        } while (current != board.go);

        assertTrue(foundUtility, "Electric Company exists");
        assertTrue(foundRailroad, "Reading Railroad exists");
    }

    @Test
    void buildBoardTiles_jailPointsToTileAfterJustVisiting() {
        Board board = new Board(new Player(false), 0);

        Tile current = board.go;
        Tile expectedNextAfterJail = null;

        do {
            if (current.getName().equals("Just Visiting")) {
                expectedNextAfterJail = current.getNextTile();
                break;
            }
            current = current.getNextTile();
        } while (current != board.go);

        assertNotNull(expectedNextAfterJail, "Just Visiting exists");
        assertSame(expectedNextAfterJail, board.jail.getNextTile());
    }

    @Test
    void runsCorrectNumberOfRounds() {
        int totalRounds = 25;

        Board board = new Board(new Player(false), totalRounds);

        int totalVisits = 0;

        Tile current = board.go;
        do {
            totalVisits += current.getVisitCounter();
            current = current.getNextTile();
        } while (current != board.go);

        totalVisits += board.jail.getVisitCounter();

        assertEquals(totalRounds, totalVisits);
    }

}
