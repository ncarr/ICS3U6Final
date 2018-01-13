public class Property extends Tile{

    private int[] rent;
    private int avocados;
    private int avocadoCost;
    private int mortgage;
    private int cost;
    private int owner;
    private String color;

    Property(String name, String color, int[] rent,
             int mortgage, int cost, int avocadoCost) {
        super(name);
        avocados = 0;
        owner = -1;

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

    public int getOwner() {
        return owner;
    }

    public void setOwner(int player) {
        owner = player;
    }

}
