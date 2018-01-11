import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel to create a new game
 * @author Nicholas Carr, Carol Chen
 */
public class GamePanel extends JPanel implements ActionListener {
    private MillenialopolyWindow window;
    private GridBagConstraints gbc;

    private JTextField usersNumField;
    private JTextField nameField;
    private int numUsers;
    private int namesCollected = 1;
    private String[] names;

    public GamePanel(MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;

        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

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


        this.add(promptPanel, gbc);
        this.add(inputPanel, gbc);
        this.add(buttonPanel, gbc);
    }


    private void getNames(int curr) {
        // Check if all names have been collected
        if (curr > numUsers){
            // Start the actual Game
            return;
        }
        this.removeAll();

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

        this.add(promptPanel, gbc);
        this.add(inputPanel, gbc);
        this.add(buttonPanel, gbc);
        this.revalidate();
        this.repaint();

        // Add the panel to the window
        this.window.add(this);


    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("Go!")) {
            numUsers = Integer.parseInt(usersNumField.getText());
            names = new String[numUsers];
            getNames(namesCollected);
        } else if (command.equals("Next")) {
            String name = nameField.getText();
            if (!name.equals("")){
                names[namesCollected - 1] = name;
                namesCollected++;
                getNames(namesCollected);
            }
        }
    }
}
