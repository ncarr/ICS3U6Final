/**
 * [HyperloopTile.java]
 * Stores a hyperloop, or "railroad"
 * @author Nicholas Carr, Carol Chen
 */

public class HyperloopTile extends Tile{

    // Costs for all hyperloops are the same and fixed 
    public static int[] fares = {25, 50, 100, 200};
    public static int mortgage = 100;
    public static int cost = 200;

    private int owner;
    private String color;
    private boolean mortgaged;

    HyperloopTile(String name) {
        super(name);
        mortgaged = false;
        owner = -1;

    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public void changeMortgage() {
        mortgaged = !mortgaged;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int player) {
        owner = player;
    }
}
