import java.util.Random;

public class Game {
    public static Board gameBoard;

    // Should actually init at real values
    public static Currency Bitcoin = new Currency(5, 20);
    public static Currency Ethereum = new Currency(2, 5);

    private int numPlayers;
    private Player[] players;

    private int currPlayer; // which player's turn at the current moment

    public Game(int numUsers, String[] names) {
        gameBoard = new Board();
        numPlayers = numUsers;
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++){
            players[i] = new Player(names[i]);
        }
        currPlayer = 0;
    }

    public void nextTurn() {
        if (currPlayer == numPlayers - 1){
            currPlayer = 0;
            Bitcoin.update();
            Ethereum.update();
        } else {
            currPlayer++;
        }
    }

    public int getCurrPlayer() {
        return currPlayer;
    }

    public Player[] getPlayers() {
        return players;
    }

}
