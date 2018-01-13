public class ChanceTile extends Tile{
    private boolean isWild;
    ChanceTile(boolean isWild) {
        this.isWild = isWild;
    }

    public String getName() {
        if (this.isWild) {
            return "Wild Chance";
        }
        return "Chance";
    }
}
