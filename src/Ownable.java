/**
 * [Ownable.java]
 * Abstract class for tiles you can own
 * @author Nicholas Carr, Carol Chen, Nathan Shen
 */

public abstract class Ownable extends Tile {// checks if tile is ownable
    // variables
    private int owner;
    protected int mortgage;
    private boolean mortgaged;
// check the state of property
    Ownable(String name) {
        super(name);
        mortgaged = false;
        owner = -1;
    }
// change/check state of property //-----------------------------------------------------------------
    public boolean isMortgaged() {
        return mortgaged;
    }

    public boolean changeMortgage() { // returns initial state
        mortgaged = !mortgaged;
        return !mortgaged;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int player) {
        owner = player;
    }

    public void clearOwner() {
        owner = -1;
    }

    public int getMortgage() {
        return mortgage;
    }
}
