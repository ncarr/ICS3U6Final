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
        this.setOpaque(true);
        this.setBackground(Color.BLUE);
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
    }
}
