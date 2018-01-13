public class ChancesInit{

    private static Game game;
    private static Player[] players;

    public interface ChanceAction {
        String execute();
    }

    public static ChanceAction[] chances = new ChanceAction[] {
        new ChanceAction() { public String execute() { return a(); } },
    };

    public static ChanceAction[] wildChances = new ChanceAction[] {
        new ChanceAction() { public String execute() { return b(); } },
    };

    public ChancesInit(Game game){
        this.game = game;
        this.players = players;
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