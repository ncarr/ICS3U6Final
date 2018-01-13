import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel to create a new game
 * @author Nicholas Carr, Carol Chen
 */
public class BoardPanel extends JPanel implements ActionListener {
    private MillenialopolyWindow window;

    private GamePanel parent;
    private Game game;
    private Player player;

    public BoardPanel(GamePanel parent, MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;
        this.parent = parent;
        this.game = parent.game;

        // Grid layout forces all rows and columns to be equally sized
        this.setLayout(new GridLayout(11, 11));
        // Iterate through all cells
        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 11; x++) {
                if (y == 0) {
                    // Top row of tiles
                    this.add(new TilePanel(TilesInit.tiles[x + 20]));
                } else if (y == 10) {
                    // Bottom row of tiles
                    this.add(new TilePanel(TilesInit.tiles[10 - x]));
                } else {
                    if (x == 0) {
                        // Left column of tiles
                        this.add(new TilePanel(TilesInit.tiles[20 - y]));
                    } else if (x == 10) {
                        // Right column of tiles
                        this.add(new TilePanel(TilesInit.tiles[y + 30]));
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
}
