/**
 * [Game.java]
 * Stores the general game proprerties
 * @author Nicholas Carr, Carol Chen
 */

import java.util.Random;
import java.awt.Color;

public class Game {
    public static Tile[] board = TilesInit.tiles; // fake

    // Should actually init at real values
    public static Currency Bitcoin = new Currency(5);
    public static Currency Ethereum = new Currency(2);

    private int numPlayers;
    private Player[] players;

    private int currPlayer; // which player's turn at the current moment

    private int govMoney; // where the taxes go

    public Game(int numUsers, String[] names, Color[] colours) {
        numPlayers = numUsers;
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++){
            players[i] = new Player(names[i], colours[i]);
        }
        currPlayer = 0;

        // Init chances
        ChancesInit chanceInit = new ChancesInit(this);
        ChancesInit.ChanceAction[] chanceCards = ChancesInit.chances;
        ChancesInit.ChanceAction[] wildChanceCards = ChancesInit.wildChances;
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
        ((Property)board[propLoc]).setOwner(getCurrPlayer()); // set property to be owned
        getPlayers()[getCurrPlayer()].addProperty((Property)board[propLoc]);  // add property to user
    }

    public void sellHyperloop(int propLoc) {
        ((HyperloopTile)board[propLoc]).setOwner(getCurrPlayer()); // set property to be owned
        getPlayers()[getCurrPlayer()].addHyperloop((HyperloopTile)board[propLoc]);  // add property to user
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
