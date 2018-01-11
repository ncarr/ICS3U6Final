import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel to setup the game
 * @author Nicholas Carr, Carol Chen
 */
public class SetupPanel extends JPanel implements ActionListener {
    private MillenialopolyWindow window;
    private GridBagConstraints gbc;

    // Data to store
    private JTextField usersNumField;
    private JTextField nameField;
    private int numUsers;
    private int namesCollected = 1;
    private String[] names;

    public SetupPanel(MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;

        // Use a grid bag layout for centering
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Take up full window

        // Add the panel to the window
        this.window.add(this);

        getNumUsers();

    }

    private void getNumUsers() {
        // prompt
        JPanel promptPanel = new JPanel();
        Font promptFont = Fonts.TITLE.deriveFont(50F);
        JLabel promptLabel = new JLabel("How many players are there?", SwingConstants.CENTER);
        promptLabel.setFont(promptFont);
        promptPanel.add(promptLabel);

        // text field
        JPanel inputPanel = new JPanel();
        usersNumField = new JTextField(2);
        // Create two borders, one to mark the box and another for padding
        Border inputBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
	    usersNumField.setBorder(BorderFactory.createCompoundBorder(
                                inputBorder,
                                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        inputPanel.add(usersNumField);

        // button
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Go!");
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        // Add panels to SetupPanel
        this.add(promptPanel, gbc);
        this.add(inputPanel, gbc);
        this.add(buttonPanel, gbc);
    }


    private void getNames(int curr) {
        // Check if all names have been collected
        if (curr > numUsers){
            window.getContentPane().remove(this); // Remove setup panel
            // Pass info into Game Panel
            window.add(new GamePanel(window, numUsers, names));
            window.getContentPane().revalidate();
            return; // Don't continue
        }

        this.removeAll(); // Remove any panels from the previous view

        // prompt
        JPanel promptPanel = new JPanel();
        Font promptFont = Fonts.TITLE.deriveFont(50F);
        JLabel promptLabel = new JLabel("What's the name of player #" + (curr ) + "?", SwingConstants.CENTER);
        promptLabel.setFont(promptFont);
        promptPanel.add(promptLabel);

        // text field
        JPanel inputPanel = new JPanel();
        nameField = new JTextField(30);
        Border inputBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        nameField.setBorder(BorderFactory.createCompoundBorder(
                                inputBorder,
                                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        inputPanel.add(nameField);

        // button
        JPanel buttonPanel = new JPanel();
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        buttonPanel.add(nextButton);

        // Add all panels
        this.add(promptPanel, gbc);
        this.add(inputPanel, gbc);
        this.add(buttonPanel, gbc);

        // Refresh to ensure that old panels are removed and new ones are addedd.
        this.revalidate();
        this.repaint();

        // Add the panel to the window
        this.window.add(this);
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        // Do nothing if input is not filled
        String command = event.getActionCommand();
        if (command.equals("Go!")) {
            try { // Try for integer parse
                if (!usersNumField.getText().equals("")){ // Must be filled
                    numUsers = Integer.parseInt(usersNumField.getText());
                    if (numUsers > 6){
                        return; // Too many people probably.
                    }
                    names = new String[numUsers]; // Set length of names
                    getNames(namesCollected);
                }
            } catch(NullPointerException e) {} catch(NumberFormatException e) {}
        } else if (command.equals("Next")) {
            String name = nameField.getText();
            if (!name.equals("")){
                names[namesCollected - 1] = name; // Set name
                namesCollected++;
                getNames(namesCollected);
            }
        }
    }
}
