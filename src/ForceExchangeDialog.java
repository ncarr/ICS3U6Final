import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;
/**
 * Panel that will force the user to exchange currency to be able to pay for x
 * @author Nicholas Carr, Carol Chen
 */
public class ForceExchangeDialog extends JDialog implements ActionListener {
    private MillenialopolyWindow window;
    private GridBagConstraints gbc;
    private JPanel mainPanel;

    private GamePanel parent;
    private Game game;
    private Player player;

    private JTextField ethField;
    private JTextField btcField;

    private int cost, receiver;

    public ForceExchangeDialog(GamePanel parent, MillenialopolyWindow window, int cost, int receiver) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;
        this.parent = parent;
        this.game = parent.game;
        this.player = game.getPlayers()[game.getCurrPlayer()];

        this.cost = cost;
        this.receiver = receiver;

        this.setLocationRelativeTo(window);

        this.setUndecorated(true); // No standard dialog decorations
        this.setVisible(true);
        this.setAlwaysOnTop(true); // Make sure that it's always on top
        this.setModal(true);

        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints(); // Constraints
        gbc.gridx = 1; // One column per row
        gbc.fill = GridBagConstraints.HORIZONTAL; // Have each item fill the row

        JPanel currencyExchangePanel = new JPanel();

        currencyExchangePanel.setLayout(new GridLayout(0, 3));
        currencyExchangePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        Font titleFont = Fonts.TITLE.deriveFont(20F);
        JLabel titleLabel = new JLabel("Exchange some currency to get " + Integer.toString(cost) + "MIL", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        currencyExchangePanel.add(titlePanel);

        // How much money they have in total
        currencyExchangePanel.add(new JLabel()); // Empty panel to padd
        currencyExchangePanel.add(new JLabel("You have " + Double.toString(player.getCurrencyTotal()) + " MIL in total."), SwingConstants.CENTER);
        currencyExchangePanel.add(new JLabel()); // empty panel


        // Show image of MIL and the amount they have
        try{
            Image milImage = ImageIO.read(new File("img/mil.png"));
            milImage = milImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            JLabel milImageLabel = new JLabel(new ImageIcon(milImage));

            currencyExchangePanel.add(new JPanel()); // Empty panel to padd
            currencyExchangePanel.add(milImageLabel);
            currencyExchangePanel.add(new JLabel(Double.toString(player.getMIL())));

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Show exchange rates
        JLabel btcExchangeLabel = new JLabel(Double.toString(game.Bitcoin.getValInMIL()) + " MIL per BTC", SwingConstants.CENTER);
        JLabel ethExchangeLabel = new JLabel(Double.toString(game.Ethereum.getValInMIL()) + " MIL per ETH", SwingConstants.CENTER);
        currencyExchangePanel.add(ethExchangeLabel);
        currencyExchangePanel.add(new JLabel());
        currencyExchangePanel.add(btcExchangeLabel);


        // Show crypto costs
        try{
            Image ethImage = ImageIO.read(new File("img/ethereum.png"));
            ethImage = ethImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            JLabel ethImageLabel = new JLabel(new ImageIcon(ethImage));

            Image btcImage = ImageIO.read(new File("img/bitcoin.png"));
            btcImage = btcImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            JLabel btcImageLabel = new JLabel(new ImageIcon(btcImage));

            currencyExchangePanel.add(ethImageLabel);
            currencyExchangePanel.add(new JPanel());
            currencyExchangePanel.add(btcImageLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Show amounts of cryptos
        JLabel btcLabel = new JLabel(Double.toString(player.getBTC()), SwingConstants.CENTER);
        JLabel ethLabel = new JLabel(Double.toString(player.getETH()), SwingConstants.CENTER);
        currencyExchangePanel.add(ethLabel);
        currencyExchangePanel.add(new JLabel());
        currencyExchangePanel.add(btcLabel);


        // Show input boxes for currency exchange
        Border inputBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        JLabel help = new JLabel("Enter all amount in MIL", SwingConstants.CENTER);

        JPanel btcInputPanel = new JPanel();
        btcField = new JTextField(10);
        btcField.setBorder(BorderFactory.createCompoundBorder(
                                inputBorder,
                                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        btcInputPanel.add(btcField);

        JPanel ethInputPanel = new JPanel();
        ethField = new JTextField(10);
        ethField.setBorder(BorderFactory.createCompoundBorder(
                                inputBorder,
                                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        ethInputPanel.add(ethField);

        currencyExchangePanel.add(ethInputPanel);
        currencyExchangePanel.add(help);
        currencyExchangePanel.add(btcInputPanel);


        // Show sell buttons
        JPanel buttonETHPanel = new JPanel();
        JButton sellETHButton = new JButton("Sell ETH");
        sellETHButton.addActionListener(this);
        buttonETHPanel.add(sellETHButton);

        JPanel buttonBTCPanel = new JPanel();
        JButton sellBTCButton = new JButton("Sell BTC");
        sellBTCButton.addActionListener(this);
        buttonBTCPanel.add(sellBTCButton);

        currencyExchangePanel.add(buttonETHPanel);
        currencyExchangePanel.add(new JPanel());
        currencyExchangePanel.add(buttonBTCPanel);

        // Add help text

        // Add finish button
        JButton backButton = new JButton("Finish");
        backButton.addActionListener(this);
        currencyExchangePanel.add(new JPanel());
        currencyExchangePanel.add(backButton);
        currencyExchangePanel.add(new JPanel());

        this.add(currencyExchangePanel);
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        // Main commands
        if (command.equals("Finish")) {
            if (player.getCurrencyTotal() < cost){
                player.spendCurrency("MIL", cost);
                if (receiver > -1){
                    game.getPlayers()[receiver].earnCurrency("MIL", cost);
                }
                dispose();
            }
        }  else if (command.equals("Sell BTC")){
            try{
                player.convertCurrency("BTC", "MIL", Double.parseDouble(btcField.getText()));
                parent.forceExchangeDialog(cost, receiver);
            } catch(Exception e){};
        } else if (command.equals("Sell ETH")){
            try{
                player.convertCurrency("ETH", "MIL", Double.parseDouble(ethField.getText()));
                parent.forceExchangeDialog(cost, receiver);
            } catch(Exception e){};
        }
    }
}
