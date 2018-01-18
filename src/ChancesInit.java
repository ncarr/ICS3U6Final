
/**
 * [ChanesInit.java]
 * Creates an array of methods that can be called
 * @author Nathan Shen, Carol Chen
 */

public class ChancesInit{

    private static Game game;
    private static Player[] players;

    public interface ChanceAction {
        String execute();
    }

    public static ChanceAction[] chances = new ChanceAction[] {
        new ChanceAction() { public String execute() { return rainMoney(); }},
        new ChanceAction() { public String execute() { return misha(); }},
        new ChanceAction() { public String execute() { return rob(); }},
        new ChanceAction() { public String execute() { return buyAvocado(); }},
        new ChanceAction() { public String execute() { return jailed(); }},
        new ChanceAction() { public String execute() { return hackathonWin(); }},
        new ChanceAction() { public String execute() { return btcMine(); }},
        new ChanceAction() { public String execute() { return ethMine(); }},
    };

    public static ChanceAction[] wildChances = new ChanceAction[] {
        new ChanceAction() { public String execute() { return takeL(); } },
        new ChanceAction() { public String execute() { return massRob(); } },
        new ChanceAction() { public String execute() { return backwards(); } },
        new ChanceAction() { public String execute() { return loseMoney(); } },
        new ChanceAction() { public String execute() { return killBtc(); } },
        new ChanceAction() { public String execute() { return killEth(); } },
    };

    public ChancesInit(Game game){
        this.game = game;
        this.players = game.getPlayers();
        return;
    }

    public static String rainMoney() { // Regular chance
        for (int i = 0; i < players.length; i++){
            players[i].earnCurrency("MIL", 200);
        }
        return "Wow! It's raining money, everyone just earned 200 MIL";
    }

    public static String misha() { // Regular chance
        Player currPlayer = players[game.getCurrPlayer()];
        double amt = game.Bitcoin.convertToMIL(currPlayer.getBTC() / 4);
        currPlayer.convertCurrency("MIL", "BTC", amt);
        currPlayer.spendCurrency("MIL", amt);
        return "Misha comes to comp sci class. lose 1/4 of your BTC ";
    }

    public static String hackathonWin() { // Regular chance
        Player currPlayer = players[game.getCurrPlayer()];
        currPlayer.earnCurrency("MIL", 100);
        return "You win a Hackathon. Earn 100MIL";
    }

    public static String buyAvocado() { // Regular chance
        Player currPlayer = players[game.getCurrPlayer()];
        currPlayer.spendCurrency("MIL", 50);
        return "You buy some avocado toast because why not. Lose 50 MIL.";
    }

    public static String ethMine() { // Regular chance
        Player currPlayer = players[game.getCurrPlayer()];
        currPlayer.earnCurrency("ETH", 2);
        return "You mined some Ethereum. Gain two ETH!";
    }

    public static String btcMine() { // Regular chance
        Player currPlayer = players[game.getCurrPlayer()];
        currPlayer.earnCurrency("BTC", 2);
        return "You mined some Bitcoin. Gain two BTC!";
    }

    public static String rob() { // Regular chance
        Player currPlayer = players[game.getCurrPlayer()];
        int earnings = 0;
        for (int i = 0; i < players.length; i++){
            if (i != game.getCurrPlayer()) {
                if (!players[i].spendCurrency("MIL", 100)){
                    players[i].spendCurrency("MIL", players[i].getMIL());
                    earnings += players[i].getMIL();
                } else {
                    earnings += 100;
                }
            }
        }
        currPlayer.earnCurrency("MIL", earnings);
        return "Steal 100 MIL from everyone. Steal all if they don't have enough MIL";
    }

    public static String jailed() { // chance
        Player currPlayer = players[game.getCurrPlayer()];
        currPlayer.goToJail();
        return "ur evil, get jailed";
    }

    public static String backwards() { // wild chance
        Player currPlayer = players[game.getCurrPlayer()];
        currPlayer.goBack();
        return "you are going backwards now";
    }

    public static String takeL() { // wild chance]
        Player currPlayer = players[game.getCurrPlayer()];
        currPlayer.lose();
        return "HOHOHOHHHHOHHOHHOHAHAHAAHAHTROLOLOLLOL U DIE \n in other words, you have lost all your properties. Good luck d00d";
    }

    public static String massRob() { // Wild
        Player currPlayer = players[game.getCurrPlayer()];
        int earnings = 0;
        for (int i = 0; i < players.length; i++){
            players[i].spendCurrency("MIL", players[i].getMIL());
            earnings += players[i].getMIL();
        }
        currPlayer.earnCurrency("MIL", earnings);
        return "Take everybody's MIL!";
    }

    public static String loseMoney() { // Wild
        Player currPlayer = players[game.getCurrPlayer()];
        currPlayer.spendCurrency("MIL", currPlayer.getMIL());
        return "You lost all your money!";
    }

    public static String killBtc() { // Wild
        game.Bitcoin.setFactorRelToMIL(0.05);
        return "Bitcoin crashed and is now worth only five cents.";
    }

    public static String killEth() { // Wild
        game.Bitcoin.setFactorRelToMIL(0.10);
        return "Ethereum crashed and is now worth only ten cents.";
    }
}
