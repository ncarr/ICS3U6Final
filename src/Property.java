public class Property extends Tile{

    private int avocados;
    private String color;
    private String tileName;
    Property(String tileName, String color, int[] rent,
             int mortgage, int cost, int avocadoCost) {
        avocados = 0;
        this.color = color;
        this.tileName = tileName;
    }

    public String getColor() {
        return this.color;
    }
    public String getName() {
        return this.tileName;
    }

}
