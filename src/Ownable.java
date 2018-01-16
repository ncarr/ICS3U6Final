/**
 * [Ownable.java]
 * Abstract class for tiles you can own
 * @author Nicholas Carr, Carol Chen
 */

public abstract class Ownable extends Tile implements Comparable<Ownable> {

    private int owner;
    protected int mortgage;
    private boolean mortgaged;

    Ownable(String name) {
        super(name);
        mortgaged = false;
        owner = -1;
    }

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

    public int getMortgage() {
        return mortgage;
    }

    /**
     * Alphabetical sort
     */
    public int compareTo(Ownable o) {
        return this.getName().compareTo(o.getName());
    }
}
