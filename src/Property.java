/**
 * [Property.java]
 * Stores a property class
 * @author Nicholas Carr, Carol Chen, Nathan Shen
 */

public class Property extends Ownable {

  // variables
    private int[] rent;
    private int avocados;
    private int avocadoCost;
    private int cost;

    private MillennialopolyColor color;

    // constructor takes data from tiles.init---------------------------------------------
    Property(String name, MillennialopolyColor color, int[] rent,
             int mortgage, int cost, int avocadoCost) {
        // Init
        super(name);
        avocados = 0;

        this.cost = cost;
        this.rent = rent;
        this.mortgage = mortgage;
        this.avocadoCost = avocadoCost;
        this.color = color;
    }
//------------------------------------------------------------------------------------------
   // Find values for property
    public MillennialopolyColor getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }

    public int getRent() {
        if (isMortgaged()){
            return 0;
        } else {
            return rent[avocados];
        }
    }

    public int[] getRentVals() {
        return rent;
    }

    public int getAvocados() {
        return avocados;
    }

    public int getAvocadoCost() {
        return avocadoCost;
    }

    public void addAvocado() {
        avocados++;
    }

    public void sellAvocado() {
        avocados--;
    }

    public void removeAvocados() {
        avocados = 0;
    }

}
