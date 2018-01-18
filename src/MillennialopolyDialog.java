/**
 * [MillennialopolyDialog.java]
 * Abstract class for JDialogs
 * @author Nicholas Carr, Carol Chen, Nathan Shen
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public abstract class MillennialopolyDialog extends JDialog implements ActionListener {
    protected MillennialopolyWindow window;
    protected GridBagConstraints gbc;
    protected JPanel mainPanel;

    protected GamePanel parent;
    protected Game game;
    protected Player player;

    public MillennialopolyDialog(GamePanel parent, MillennialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();

        // Store game info
        this.window = window;
        this.parent = parent;
        this.game = parent.game;
        this.player = game.getPlayers()[game.getCurrPlayer()];

        Rectangle r = window.getBounds();

        // Each tile is 1/11 of the screen
        int height = (int)(r.height * (9.0 / 11) - 50);
        int width = (int)(r.width * (9.0 / 11) - 50);
        this.setSize(width, height);  // Don't have it take up full width
        this.setLocation(r.width / 2 - this.getWidth() / 2, r.height / 2 - this.getHeight() / 2 + 10);

        this.setUndecorated(true); // No standard dialog decorations
        this.setVisible(true); // will make sure that the program stops until this closes
        this.setAlwaysOnTop(true); // Make sure that it's always on top

        // panel in the dialog
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        // Layouting
        gbc = new GridBagConstraints(); // Constraints
        gbc.gridx = 1; // One column per row
        gbc.fill = GridBagConstraints.HORIZONTAL; // Have each item fill the row

        this.add(mainPanel);

    }

    /**
    * Method called when any button is pressed
    */
    public abstract void actionPerformed(ActionEvent event);

    public abstract void loadMain();

    public void loadPropertyManager(){
        mainPanel.removeAll(); // Remove any panels from the previous view
        mainPanel.add(new PropertyManager(this, window));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
