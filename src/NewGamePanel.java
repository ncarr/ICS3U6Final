import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel to create a new game
 * @author Nicholas Carr
 */
public class NewGamePanel extends JPanel implements ActionListener {
  public WelcomePanel parent;

  public NewGamePanel(WelcomePanel parent) {
    // Create a JPanel and add the buttons
    super();
    this.parent = parent;

    JLabel label = new JLabel("Coming soon");

    // Add the buttons to the JPanel
    this.add(label);

    // Add the panel to the window
    parent.window.add(this);
  }

  /**
   * Method called when any button is pressed
   */
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();

    // TODO: Add buttons and handle actions
  }
}
