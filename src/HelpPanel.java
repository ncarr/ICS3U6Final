/**
 * [HelpPanel.java]
 * Provides user with instructions
 * @author Nathan Shen, Nicholas Carr, Carol Chen
 */
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelpPanel extends JDialog {

  public HelpPanel(Frame owner) {
    // Create a JDialog
    super(owner, "Help", true);
    // Add instructions
    try {
        Image ethImage = ImageIO.read(new File("img/help.png"));
        //ethImage = ethImage.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        // Create a component to hold the image
        JLabel contentPane = new JLabel(new ImageIcon(ethImage));
        // Make sure it fills the dialog
        contentPane.setLayout(new BorderLayout());
        // Set it as the content pane so it scales
        this.setContentPane(contentPane);
        // Make the background look good and resize the dialog to fit its contents
        this.setBackground(Color.white);
        this.pack();
        this.setVisible(true);
    } catch (IOException e) {
        this.add(new JLabel("Something went wrong loading the image here"));
    }

  }
}
