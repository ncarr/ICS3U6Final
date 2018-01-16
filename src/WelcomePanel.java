/**
 * [WelcomePanel.java]
 * Opening panel with options to start a game or get help
 * @author Nicholas Carr, Carol Chen
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main menu panel
 * @author Nicholas Carr, Carol Chen
 */
public class WelcomePanel extends JPanel implements ActionListener {
  private MillennialopolyWindow window;

  public WelcomePanel(MillennialopolyWindow window) {
    // Create a JPanel and add the buttons
    super();

    // Set layout to GridBag for easy vertical and horizontal centering
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints(); // Constraints
    gbc.gridx = 1; // One column per row
    gbc.fill = GridBagConstraints.HORIZONTAL; // Have each item fill the row

    this.window = window; // Store window
    this.window.setExtendedState(JFrame.MAXIMIZED_BOTH); // make sure Panel takes up full height

    // Title Text
    JPanel titlePanel = new JPanel();
    Font titleFont = Fonts.TITLE.deriveFont(100F);
    JLabel titleLabel = new JLabel("Millennialopoly", SwingConstants.CENTER);
    titleLabel.setFont(titleFont);
    titlePanel.add(titleLabel);

    // button options
    JPanel buttonPanel = new JPanel();
    JButton playButton = new JButton("Play");
    JButton resumeButton = new JButton("Resume saved game");
    JButton helpButton = new JButton("Help");
    JButton quitButton = new JButton("Quit");

    // Add action listeners to the buttons
    playButton.addActionListener(this);
    resumeButton.addActionListener(this);
    helpButton.addActionListener(this);
    quitButton.addActionListener(this);

    // Add the buttons to the JPanel
    buttonPanel.add(playButton);
    buttonPanel.add(resumeButton); // Will we ever do this?
    buttonPanel.add(helpButton);
    buttonPanel.add(quitButton);

    // Add panels to the WelcomePanel
    this.add(titlePanel, gbc);
    this.add(buttonPanel, gbc);

    // Add the panel to the window
    window.add(this);
    window.revalidate();
    window.repaint();
  }

  /**
   * Method called when any button is pressed
   */
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();
    if (command.equals("Play")) {
        window.getContentPane().remove(this); // remove welcome
        window.add(new SetupPanel(window)); // Move to setup
        window.getContentPane().revalidate(); // revalidate to refresh
    } else if (command.equals("Resume saved game")) {
        // this.window.add(new ResumePanel(this));
    } else if (command.equals("Help")) {
        // this.window.add(new HelpPanel(this));
    } else if (command.equals("Quit")) {
        // this.window.dispose();
    }
  }
}
