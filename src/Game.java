import java.util.Random;

public class Game {
    public static Board board = new Board();

    // Should actually init at real values
    public static Currency Bitcoin = new Currency(5);
    public static Currency Ethereum = new Currency(2);

    private int numPlayers;
    private Player[] players;

    private int currPlayer; // which player's turn at the current moment

    private int govMoney;

    public Game(int numUsers, String[] names) {
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

    public void sellProperty(int propLoc) {
        ((Property)board.tiles[propLoc]).setOwner(getCurrPlayer()); // set property to be owned
        getPlayers()[getCurrPlayer()].addProperty((Property)board.tiles[propLoc]);  // add property to user
    }

    public int getCurrPlayer() {
        return currPlayer;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void addToTaxes(int taxes){
        govMoney += taxes;
    }

    public int performCommunism(){
        int res = govMoney;
        govMoney = 0;
        return res;
    }
}
