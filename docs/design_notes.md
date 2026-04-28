Notes on the design and structure of the program as we've written it.

## Movement
One of the big questions I had to ask when scaffolding was "How do we handle the movement of the player?" Does the player move itself, or does it get moved by the board? Should this change for gradual vs direct movement? Certainly it has to in part move itself, since it's responsible for the get-out-of-jail strategy, but it also has to be moved by the board since the board is responsible for holding references to special squares, and sometimes the player will have to go to jail, or go, or any number of individual tiles.

The solution I decided on was that the board would perform all the movement, but for the dice-roll part of the turn (each turn has 4 phases), the player would dictate how much it would like to move. In the case of being in jail, the board simply informs the player that it's in jail, and there's an implicit agreement that choosing to move out of jail is because the player got doubles, used a card, or paid the fine, though the board doesn't validate any of that.

And then for direct movement, like going directly to jail, the board handles it as well, either placing the player directly on a stored reference to that tile, or traversing the board until it finds the title with the proper name.

## Lists
Implementing Monopoly differs from other games like Mario in that Monopoly is largely a game of lists. There are 40 tiles, and dozens more cards from multiple decks with custom functionality and sometimes unique mechanics. That's the majority of the game, so we had to decide how to hold the functionality and unique data of so many different pieces of the game. Do we make an individual subclass for each kind of card and tile? Do we only keep a list of names or numbers and put the rest in a massive switch statement?

We ended up doing something close to the latter. The tiles especially are pretty much the same, as far as we're concerned, so we made a list of 41 tiles (one extra for the prison) and dumped them in a text file with a parser to turn them into tiles. I anticipate we'll do something similar for the cards, but as of the checkpoint we haven't gotten that far yet.

## Other

### Cards
As required, we've implemented full deck shuffling mechanics, with each deck having a draw and discard pile. Because these are used in order, and then shuffled and reset all at once, it doesn't really matter what data structure we use for the draw and discard piles. A queue or a stack would have been just fine, but I got lazy and went with an `ArrayList`. The shuffling is handled by `Collections.shuffle()`.

The get out of jail cards can be added to a player's inventory, so we have a queue for each player to hold them, along with a pointer and method for the card to return to a specific discard pile once used. The discard pile pointer is set when the card is drawn, so no card should ever exit the game (but integration testing for this fact would still be helpful, if we get around to it).

### Overall flow
As mentioned above, this project has all player movement handled by the Board. What other decisions were made to organize the project?

- Each simulation of a game is a distinct board object (automatically run from the constructor), so running 80 total simulations means we're creating a total of 80 Boards (even more if you count dummy boards for printing statistics).
	- We have a dummy game with 0 turns because the tiles are created inside the board's constructor, and we need to print out the names of the tiles as a header for our CSV, so we just run the game one more time.
	- We're not directly writing the CSV to a file, we're just printing it to the terminal. This is because I use bash, and I think it's easier to write `java src/main/Board.java > results/output.csv` every time than to hook it up to an actual file writer.
- The number of times each tile has been visited per game is kept track of in the Tile class, and at the end of a game each tile is looped over and its visit count is printed, separated by commas, to be imported into Excel.
- Each card has function that's called when it's drawn. For normal cards, that function will always include moving itself to the discard pile (to clarify, the cards are given the location of their intended discard pile, but the cards themselves are responsible for putting themselves there). Jail cards don't put themselves in the discard pile immediately, because they're given to the player first, and only eventually moved to the discard.
	- I tried calling the method the player calls to return the card to its discard pile `return` and Java got mad at me because that's the name of its firstborn child or something.
- The four phases of each player's turn are thus:
	1. ask the player how much it wants to move (the board rolls the dice and tells the player if it's in jail, and moves it however many squares it decides)
	2. if the player has how had 3 doubles in a row, send it to jail (it keeps track but the board queries and moves it)
	3. check the player's current tile. if it has an action (like drawing a card or going to jail), do it
	4. finally, modify the visit counter of the player's current tile
