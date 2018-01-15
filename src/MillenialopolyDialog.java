/**
 * [MillenialopolyDialog.java]
 * Abstract class for JDialogs
 * @author Nicholas Carr, Carol Chen
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public abstract class MillenialopolyDialog extends JDialog implements ActionListener {
    protected MillenialopolyWindow window;
    protected GridBagConstraints gbc;
    protected JPanel mainPanel;

    protected GamePanel parent;
    protected Game game;
    protected Player player;

    public MillenialopolyDialog(GamePanel parent, MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();

        // Store game info
        this.window = window;
        this.parent = parent;
        this.game = parent.game;
        this.player = game.getPlayers()[game.getCurrPlayer()];

        Rectangle r = window.getBounds();
        this.setSize(r.width - 350, r.height - 250);  // Don't have it take up full width
        this.setLocationRelativeTo(window);

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
