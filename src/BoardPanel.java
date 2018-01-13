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

    public BoardPanel(MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;

        this.setLayout(new GridBagLayout());
        // Left from bottom right
        for (int i = 0, col = 10; i < 10; i++, col--) {
          this.addTile(i, 10, col);
        }
        // Up from bottom left
        for (int i = 10, row = 10; i < 20; i++, row--) {
          this.addTile(i, row, 0);
        }
        // Right from top left
        for (int i = 20, col = 0; i < 30; i++, col++) {
          this.addTile(i, 0, col);
        }
        // Down from top right
        for (int i = 30, row = 0; i < 40; i++, row++) {
          this.addTile(i, row, 10);
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
    /**
    * Add a tile to the board in the given position
    */
    private void addTile(int index, int row, int col) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        TilePanel tile = new TilePanel(TilesInit.tiles[index]);
        this.add(tile, constraints);
    }
}
