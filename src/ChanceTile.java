/**
 * [ChanceTile.java]
 * Class for a chance tile
 * @author Nicholas Carr, Carol Chen, Nathan Shen
 */

public class ChanceTile extends Tile{//Defines chance tiles on board

    private boolean isWild;// check what type of chance it is

    ChanceTile(String name, boolean isWild) {
        super(name);
        this.isWild = isWild;
    }

    public boolean isWild(){
         return isWild;
    }
}
