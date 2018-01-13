import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    public Tile tile;

    public TilePanel(Tile tile) {
        super();
        this.tile = tile;
        this.setOpaque(true);
        if (tile instanceof Property) {
            Property property = (Property) tile;
            if (property.getColor().equals("brown")) {
                this.setBackground(new Color(165, 42, 42));
            } else if (property.getColor().equals("light blue")) {
                this.setBackground(new Color(173, 216, 230));
            } else if (property.getColor().equals("magenta")) {
                this.setBackground(Color.magenta);
            } else if (property.getColor().equals("orange")) {
                this.setBackground(Color.orange);
            } else if (property.getColor().equals("red")) {
                this.setBackground(Color.red);
            } else if (property.getColor().equals("yellow")) {
                this.setBackground(Color.yellow);
            } else if (property.getColor().equals("green")) {
                this.setBackground(Color.green);
            } else if (property.getColor().equals("blue")) {
                this.setBackground(Color.blue);
            }
        } else {
            this.setBackground(Color.white);
        }
    }
}
