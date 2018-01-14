import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private Tile tile;
    private JTextArea titleLabel;
    private Game game;
    private int index;

    public TilePanel(Game game, int index) {
        super();
        this.index = index;
        this.tile = TilesInit.tiles[index];
        this.game = game;
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
        // Add the tile's name
        Font titleFont = Fonts.TITLE.deriveFont(15F);
        // JTextAreas support text wrapping
        titleLabel = new JTextArea(tile.getName());
        // Make it look like a JLabel
        titleLabel.setBackground(null);
        titleLabel.setEditable(false);
        titleLabel.setBorder(null);
        titleLabel.setLineWrap(true);
        titleLabel.setWrapStyleWord(true);
        titleLabel.setFocusable(false);
        titleLabel.setFont(titleFont);
        this.add(titleLabel);
        // Indicate whether a player is on this tile
        this.refreshBorder();
        this.refreshAvocados();
    }

    /**
    * Refresh the state of the tile after each turn
    */
    public void refresh() {
        this.refreshBorder();
        this.removeAll();
        this.add(titleLabel);
        this.refreshAvocados();
    }

    /**
    * Reset the border to the current players
    */
    public void refreshBorder() {
        Border border = BorderFactory.createLineBorder(Color.white);
        Player[] players = game.getPlayers();
        for (int i = 0; i < players.length; i++) {
            if (players[i].getLocation() == index) {
                Border outer = BorderFactory.createLineBorder(players[i].getColour(), 2);
                border = BorderFactory.createCompoundBorder(border, outer);
            }
        }
        Border outer = BorderFactory.createLineBorder(Color.white);
        border = BorderFactory.createCompoundBorder(border, outer);
        this.setBorder(border);
    }

    public void refreshAvocados() {
        if (tile instanceof Property) {
            Property property = (Property) tile;
            for (int i = 0; i < property.getAvocados(); i++) {
                JLabel label = new JLabel("O");
                label.setForeground(Color.green);
                this.add(label);
            }
        }
    }
}
