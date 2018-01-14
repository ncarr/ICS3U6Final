public class Property extends Tile{

    private int[] rent;
    private int avocados;
    private int avocadoCost;
    private int mortgage;
    private int cost;
    private int owner;
    private String color;
    private boolean mortgaged;

    Property(String name, String color, int[] rent,
             int mortgage, int cost, int avocadoCost) {
        super(name);
        avocados = 0;
        owner = -1;
        mortgaged = false;

        this.cost = cost;
        this.rent = rent;
        this.mortgage = mortgage;
        this.avocadoCost = avocadoCost;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }

    public int getRent() {
        if (mortgaged){
            return 0;
        } else {
            return rent[avocados];
        }
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

    public int getMortgage() {
        return mortgage;
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
