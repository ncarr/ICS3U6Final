/**
 * [ChanceTile.java]
 * Class for a chance tile
 * @author Nicholas Carr, Carol Chen
 */

public class ChanceTile extends Tile{

    private boolean isWild;

    ChanceTile(String name, boolean isWild) {
        super(name);
        this.isWild = isWild;
    }

    public boolean isWild(){
         return isWild;
    }
}
