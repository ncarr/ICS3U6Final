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

        BoardPanel boardComponent = new BoardPanel(this, window);
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
        ctrlComponent.dispose();
        int roll = Player.roll();
        int newLoc = game.getPlayers()[game.getCurrPlayer()].move(roll);
        JOptionPane.showMessageDialog(null, "nthnthuntoheu", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);

        // handle the new location, pay if needed, prompt for purchase if needed

        ctrlComponent = new ControlPanel(this, window);
    }
}
