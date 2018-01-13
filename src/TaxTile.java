public class TaxTile extends Tile{

    private int cost;
    private String name;

    TaxTile(String name, int taxes) {
        super(name);
        cost = taxes;
    }

    public int getCost(){
        return cost;
    }
}
