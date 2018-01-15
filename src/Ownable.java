/**
 * [Ownable.java]
 * Abstract class for tiles you can own
 * @author Nicholas Carr, Carol Chen
 */

public abstract class Ownable extends Tile {

    private int owner;
    private boolean mortgaged;

    Ownable(String name) {
        super(name);
        mortgaged = false;
        owner = -1;
    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public boolean changeMortgage() {
        return mortgaged = !mortgaged;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int player) {
        owner = player;
    }
}
