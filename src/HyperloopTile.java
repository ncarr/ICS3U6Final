/**
 * [HyperloopTile.java]
 * Stores a hyperloop, or "railroad"
 * @author Nicholas Carr, Carol Chen
 */

public class HyperloopTile extends Ownable implements Comparable<Ownable> {

    // Costs for all hyperloops are the same and fixed
    public static int[] fares = {25, 50, 100, 200};
    public static int cost = 200;


    HyperloopTile(String name) {
        super(name);
        this.mortgage = 100;
    }

    public int compareTo(Ownable o) {
        if (o instanceof Property) {
            return 1;
        } else if (o instanceof UtilityTile) {
            return -1;
        } else {
            return super.compareTo(o);
        }
    }
}
