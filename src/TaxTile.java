public class TaxTile extends Tile{

    private int cost;

    TaxTile(int taxes) {
        cost = taxes;
    }

    public int getCost(){
        return cost;
    }

    public String getName() {
        return "Tax";
    }
}
