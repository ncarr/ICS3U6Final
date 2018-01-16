/**
 * [MillennialopolyWindow.java]
 * Main window / frame
 * @author Nicholas Carr, Carol Chen
 */

import javax.swing.*;
import java.awt.*;

/**
 * Main game window
 * @author Nicholas Carr
 */
public class MillennialopolyWindow extends JFrame {

  public MillennialopolyWindow() {
    super();

    // Set the properties of the window
    this.setTitle("Millennialopoly");
    this.pack();

    // Make it visible
    this.setVisible(true);
    // End the program when this window is closed
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
