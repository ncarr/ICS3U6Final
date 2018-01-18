
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
        new ChanceAction() { public String execute() { return rainMoney(); } },
        new ChanceAction() { public String execute() { return misha(); } },
        new ChanceAction() { public String execute() { return rob(); } },
        new ChanceAction() { public String execute() { return jailed(); } },
    };

    public static ChanceAction[] wildChances = new ChanceAction[] {
        new ChanceAction() { public String execute() { return takeL(); } },
        new ChanceAction() { public String execute() { return backwards(); } },
    };

    public ChancesInit(Game game){
        this.game = game;
        this.players = game.getPlayers();
        return;
    }

    public static String rainMoney() { // Regular chance
        for (int i = 0; i < players.length; i++){
            players[i].earnCurrency("MIL", 500);
        }
        return "Wow! It's raining money, everyone just earned 500 MIL";
    }

    public static String misha() { // Regular chance
        Player currPlayer = players[game.getCurrPlayer()];
        double amt = game.Bitcoin.convertToMIL(currPlayer.getBTC() / 4);
        currPlayer.convertCurrency("MIL", "BTC", amt);
        currPlayer.spendCurrency("MIL", amt);
        return "Misha comes to comp sci class. lose 1/4 of your BTC ";
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


}
