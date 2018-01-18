/**
 * [BoardPanel.java]
 * Controls the game board visually
 * @author Nicholas Carr, Carol Chen
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardPanel extends JPanel implements ActionListener {
    private MillennialopolyWindow window;

    private GamePanel parent;
    private Game game;
    private Player player;
    private TilePanel[] tiles;

    public BoardPanel(GamePanel parent, MillennialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;
        this.parent = parent;
        this.game = parent.game;

        // Grid layout forces all rows and columns to be equally sized
        this.setLayout(new GridLayout(11, 11));
        // Create all tiles
        this.tiles = new TilePanel[40];
        for (int i = 0; i < 40; i++) {
            this.tiles[i] = new TilePanel(game, i);
        }
        // Add them to the board
        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 11; x++) {
                if (y == 0) {
                    // Top row of tiles
                    this.add(tiles[x + 20]);
                } else if (y == 10) {
                    // Bottom row of tiles
                    this.add(tiles[10 - x]);
                } else {
                    if (x == 0) {
                        // Left column of tiles
                        this.add(tiles[20 - y]);
                    } else if (x == 10) {
                        // Right column of tiles
                        this.add(tiles[y + 30]);
                    } else {
                        // Empty inner area
                        this.add(new JPanel());
                    }
                }
            }
        }
        this.setOpaque(true);
        this.setBackground(Color.black);
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
    }

    public void refresh() {
        // Refresh all tiles
        for (int i = 0; i < 40; i++) {
            tiles[i].refresh();
        }
        // Redraw the board
        this.revalidate();
        this.repaint();
    }
}
