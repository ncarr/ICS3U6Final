import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main menu panel
 * @author Nicholas Carr, Carol Chen
 */
public class WelcomePanel extends JPanel implements ActionListener {
  private MillenialopolyWindow window;

  public WelcomePanel(MillenialopolyWindow window) {
    // Create a JPanel and add the buttons
    super();

    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    this.window = window;
    this.window.setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Title Text
    JPanel titlePanel = new JPanel();
    Font titleFont = Fonts.TITLE.deriveFont(100F);
    JLabel titleLabel = new JLabel("Millenialopoly", SwingConstants.CENTER);
    titleLabel.setFont(titleFont);
    titlePanel.add(titleLabel);

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


    this.add(titlePanel, gbc);
    this.add(buttonPanel, gbc);

    // Add the panel to the window
    window.add(this);
  }

  /**
   * Method called when any button is pressed
   */
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();
    if (command.equals("Play")) {
        window.getContentPane().remove(this);
        window.add(new GamePanel(window));
        window.getContentPane().revalidate();
    } else if (command.equals("Resume saved game")) {
        // this.window.add(new ResumePanel(this));
    } else if (command.equals("Help")) {
        // this.window.add(new HelpPanel(this));
    } else if (command.equals("Quit")) {
        // this.window.dispose();
    }
  }
}
