import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel to create a new game
 * @author Nicholas Carr, Carol Chen
 */
public class GamePanel extends JPanel implements ActionListener {
    private MillenialopolyWindow window;

    private int numUsers;
    private String[] names;

    public GamePanel(MillenialopolyWindow window, int users, String[] names) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;

        this.setLayout(new BorderLayout());

        BoardPanel boardComponent = new BoardPanel(window);
        ControlPanel ctrlComponent = new ControlPanel(window);

        this.add(boardComponent);

        // Add the panel to the window
        this.window.add(this);


    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
    }
}
