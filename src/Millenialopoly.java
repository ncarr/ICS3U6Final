/* [Millenialopoly.java]
 * A Monopoly variation adapted to suit "Millenials" a.k.a. Snake People
 * AUTHORS: Carol Chen, Nicholas Carr
 */

class Millenialopoly {

    // MIL is the currency used in Millenialopoly
    private static double startingMoneyMIL = 1500;

    public Player[] players;
    public int numPlayers;

    public Board gameBoard;

    // Should actually init at real values
    public Currency Bitcoin = new Currency(10000);
    public Currency Ethereum = new Currency(500);

    public static void main(String[] args) {
        // Start the shenanigans!

        numPlayers = 3; // Change to input

        gameBoard = new Board;

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++){
            players[i] = new Player("Player No." + i) // Change to taking a name input
        }

        // Game loop goes here
    }

}
