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

    private static ChancesInit.ChanceAction[] chanceCards;
    private static ChancesInit.ChanceAction[] wildChanceCards;

    public Game(int numUsers, String[] names, Color[] colours) {
        numPlayers = numUsers;
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++){
            players[i] = new Player(names[i], colours[i]);
        }
        currPlayer = 0;

        // Init chances
        ChancesInit chanceInit = new ChancesInit(this);
        chanceCards = ChancesInit.chances;
        wildChanceCards = ChancesInit.wildChances;
    }

    public void nextTurn() {
        players[currPlayer].nextTurn();
        if (currPlayer == numPlayers - 1){
            currPlayer = 0;
            Bitcoin.update();
            Ethereum.update();
        } else {
            currPlayer++;
        }
    }

    public void sellOwnable(int propLoc) {
        ((Ownable)board[propLoc]).setOwner(getCurrPlayer()); // set property to be owned
        if (board[propLoc] instanceof Property){
            getPlayers()[getCurrPlayer()].addProperty((Property)board[propLoc]);
        } else if (board[propLoc] instanceof UtilityTile){
            getPlayers()[getCurrPlayer()].addUtility((UtilityTile)board[propLoc]);
        } else if (board[propLoc] instanceof HyperloopTile){
            getPlayers()[getCurrPlayer()].addHyperloop((HyperloopTile)board[propLoc]);
        }
    }

    public int getCurrPlayer() {
        return currPlayer;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void addToTaxes(int taxes){
        govMoney += taxes;
    }

    public int performCommunism(){
        int res = govMoney;
        govMoney = 0;
        return res;
    }

    public boolean killCurrPlayer(){ // return true if one player remaining
        getPlayers()[getCurrPlayer()].lose();
        getPlayers()[getCurrPlayer()] = null;
        int c = numPlayers;
        for (Player p: getPlayers()){
            if (p == null){
                c--;
            }
        }
        if (c == 1){
            return true;
        }
        return false;
    }

    public String drawCard(boolean wild){
        Random rand = new Random();
        if (wild){
            int len = wildChanceCards.length;
            ChancesInit.ChanceAction card = wildChanceCards[rand.nextInt(len)];
            return card.execute();
        } else {
            int len = chanceCards.length;
            ChancesInit.ChanceAction card = chanceCards[rand.nextInt(len)];
            return card.execute();
        }
    }
}
