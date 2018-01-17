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
    public static ChanceAction[] wildChances;
    public static ChanceAction[] chances;

    public ChancesInit(Game game){
        this.game = game;
        this.players = game.getPlayers();
        chances = new ChanceAction[] {
            new ChanceAction() { public String execute() { return a(); } },
        };

        wildChances = new ChanceAction[] {
            new ChanceAction() { public String execute() { return b(); } },
        };
        return;
    }

    public static String a() {
        for (int i = 0; i < players.length; i++){
            players[i].earnCurrency("MIL", 500);
        }
        return "Wow! It's raining money, everyone just earned 500 MIL";
    }


    public static String b() {
        // do something dumb
        return "HOHOHOHHHHOHHOHHOHAHAHAAHAHTROLOLOLLOL";
    }
}