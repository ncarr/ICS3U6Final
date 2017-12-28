import javax.swing.*;
import java.awt.*;

/**
 * Main game window
 * @author Nicholas Carr
 */
public class GameFrame extends JFrame {
  public Game state;
  
  public GameFrame(Game state) {
    super();

    this.state = state;
    // Set the properties of the window
    this.setTitle("Millenialopoly");
    this.setSize(640, 320);

    // Make it visible
    this.setVisible(true);
    // End the program when this window is closed
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
