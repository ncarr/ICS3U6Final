/* [Millenialopoly.java]
 * A Monopoly variation adapted to suit "Millenials" a.k.a. Snake People
 * AUTHORS: Carol Chen, Nicholas Carr
 */
import java.util.Random;

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

        // Start with welcome panel, instructions, etc

        numPlayers = 3; // Change to input

        gameBoard = new Board;

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++){
            players[i] = new Player("Player No." + i) // Change to taking a name input
        }

        // Add some kind of "start game" thingy

        // Game loop
        while (true){
            for (int i = 0; i < numPlayers; i++){
                Random dice = new Random();
                int roll = rand.nextInt(6) + 1;
                players[i].move(roll);

                // Temporary scanner solution for testing
                Scanner sc = new Scanner(System.in);

                // if need to pay, pay.
                // If not enough money to pay, mortage options
                while (true){
                    int f = sc.nextInt();
                    // Actual signals will change
                    if (f == 0){ // end
                        break;
                    } else if (f == 1){ //purchase if avail

                    } else if (f == 2){ //auction if avail

                    } else if (f == 3){ // crypto trade

                    } else if (f == 4){ // request trade with user

                    } else if (f == 5){ // build avocado

                    } else if (f == 6){ // manage mortages

                    }
                }
                // dialog to pass to next player
            }
            Bitcoin.update();
            Ethereum.update();
        }

    }

}
