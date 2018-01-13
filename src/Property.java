public class Property extends Tile{

    private int avocados;
    private String color;
    Property(String tileName, String color, int[] rent,
             int mortgage, int cost, int avocadoCost) {
        avocados = 0;
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

}
