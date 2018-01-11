import javax.swing.*;
import java.awt.*;

/**
 * Main game window
 * @author Nicholas Carr
 */
public class MillenialopolyWindow extends JFrame {
  public Game state;

  public MillenialopolyWindow() {
    super();

    // Set the properties of the window
    this.setTitle("Millenialopoly");
    this.pack();

    // Make it visible
    this.setVisible(true);
    // End the program when this window is closed
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
