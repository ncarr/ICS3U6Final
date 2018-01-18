public class Tile{

    private int[] players; // -1 if no player on tile
    private String name;
    Tile(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
