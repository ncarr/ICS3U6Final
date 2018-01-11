import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    public Tile tile;

    public TilePanel(Tile tile) {
        super();
        this.tile = tile;
        this.setOpaque(true);
        this.setBackground(Color.GREEN);
    }
}
