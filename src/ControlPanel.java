import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel that contains options for users to take during a move
 * @author Nicholas Carr, Carol Chen
 */
public class ControlPanel extends JDialog implements ActionListener {
    private MillenialopolyWindow window;

    public ControlPanel(MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;

        this.setModal(true); // always on top

        Rectangle r = window.getBounds();
        this.setSize(r.width - 300, r.height - 300);
        this.setLocationRelativeTo(window);

        this.setUndecorated(true);
        this.setVisible(true);
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
    }
}
