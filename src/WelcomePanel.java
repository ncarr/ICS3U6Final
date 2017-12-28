import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main menu panel
 * @author Nicholas Carr
 */
public class WelcomePanel extends JPanel implements ActionListener {
  public GameFrame window;

  public WelcomePanel(GameFrame window) {
    // Create a JPanel and add the buttons
    super();

    this.window = window;

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
    this.add(playButton);
    this.add(resumeButton);
    this.add(helpButton);
    this.add(quitButton);

    // Add the panel to the window
    window.add(this);
  }

  /**
   * Method called when any button is pressed
   */
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();

    if (command.equals("Play")) {
      this.window.add(new NewGamePanel(this));
    } else if (command.equals("Resume saved game")) {
      this.window.add(new ResumePanel(this));
    } else if (command.equals("Help")) {
      this.window.add(new HelpPanel(this));
    } else if (command.equals("Quit")) {
      this.window.dispose();
    }
  }
}
