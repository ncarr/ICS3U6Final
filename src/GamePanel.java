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
    private ControlPanel ctrlComponent;

    public Game game;

    public GamePanel(MillenialopolyWindow window, int users, String[] names) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;

        this.setLayout(new BorderLayout());

        BoardPanel boardComponent = new BoardPanel(window);
        this.add(boardComponent); // Add board to window

        // Add the panel to the window
        this.window.add(this);

        game = new Game(users, names);

        ctrlComponent = new ControlPanel(this, window);
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
    }

    public void endTurn() {
        game.nextTurn();
        // do roll, pay if needed, buy if needed
        ctrlComponent = new ControlPanel(this, window);
    }
}
