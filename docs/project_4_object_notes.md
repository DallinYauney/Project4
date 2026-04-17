**Objects:**  
Player 

- Inventory (Queue) for get out of jail cards

Board 

- Contains array of tiles (use array for ease of navigating when drawing cards and go to x spaces).  
- Array of size 41, indices 0-40. (41 tiles to handle visiting vs in jail)  
- Track the total number of moves made.

Tile 

- Stored in /resources/tiles  
- 41 in total (extra jail tile to distinguish visiting vs in jail)

Deck 

- Stack/queue for each draw pile. Stack for each discard. When draw pile is empty, randomly take cards from discard and add to draw until discard is empty to “shuffle”

Card 

- 2 separate sub classes  
  - get out of jail cards  
    - Goes into player inventory  
  - movement cards  
    - Has an index for space to go to  
- 2 decks  
  - Jail cards have attribute pointing to correct discard pile  
  - movement card discarding is handled when drawn from draw pile  
  - community cards in /resources/community  
  - chance cards in /resources/chance

**Functionality TBD:**

- Track how many times the simulation lands on each square.  
  - Incrementing landing counter for the square you end on (after all movement effects are resolved) **Does this mean that a player never lands on Go To Jail or if they pick up a movement card, they don’t “land” on the chance/community chest space?**  
- Rolling two dice and moving forward that many spaces.  
- Tracking consecutive doubles; on the third consecutive double in a single turn, send the player to Jail.  
- Resolving Chance/Community Chest cards  
- Resolving “Go to Jail” square.  
- Jail exit strategies  
- Batch Simulation and Data Collection  
- Comparative Output / Run Summary