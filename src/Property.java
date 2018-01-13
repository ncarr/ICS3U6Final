public class Property extends Tile{

    private int[] rent;
    private int avocados;
    private int avocadoCost;
    private int mortgage;
    private int cost;
    private int owner;
    private String color;
    private String name;

    Property(String tileName, String color, int[] rent,
             int mortgage, int cost, int avocadoCost) {
        avocados = 0;
        owner = -1;


        this.name = tileName;
        this.cost = cost;
        this.rent = rent;
        this.mortgage = mortgage;
        this.avocadoCost = avocadoCost;
        this.color = color;
        this.tileName = tileName;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
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
    public String getName() {
        return this.tileName;
    }

}
