/**
 * [HyperloopTile.java]
 * Stores a hyperloop, or "railroad"
 * @author Nicholas Carr, Carol Chen
 */

public class HyperloopTile extends Ownable {

    // Costs for all hyperloops are the same and fixed
    public static int[] fares = {25, 50, 100, 200};
    public static int cost = 200;


    HyperloopTile(String name) {
        super(name);
        this.mortgage = 100;
    }

}
