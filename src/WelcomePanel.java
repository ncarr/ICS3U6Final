import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main menu panel
 * @author Nicholas Carr, Carol Chen
 */
public class WelcomePanel extends JPanel implements ActionListener {
  public GameFrame window;

  public WelcomePanel(GameFrame window) {
    // Create a JPanel and add the buttons
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.window = window;

    this.window.setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Title Text
    JPanel titlePanel = new JPanel();
    titlePanel.setBorder(new EmptyBorder(300, 0, 0, 0)); // padding
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


    this.add(titlePanel);
    this.add(buttonPanel);

    // Add the panel to the window
    window.add(this);
  }

  /**
   * Method called when any button is pressed
   */
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();

    if (command.equals("Play")) {
      this.window.add(new GamePanel(this));
    } else if (command.equals("Resume saved game")) {
      this.window.add(new ResumePanel(this));
    } else if (command.equals("Help")) {
      this.window.add(new HelpPanel(this));
    } else if (command.equals("Quit")) {
      this.window.dispose();
    }
  }
}
