/**
 * [ForceExchangeDialog.java]
 * This panel shows up for users to sell currency, mortgage proprties and sell avocades when needed
 * @author Nicholas Carr, Carol Chen
 */

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

public class ForceExchangeDialog extends MillennialopolyDialog implements ActionListener {

    private JTextField ethField;
    private JTextField btcField;

    private int cost, receiver; // the amount that needs to be paid an the index of the receiver, -1 if none

    public ForceExchangeDialog(GamePanel parent, MillennialopolyWindow window, int cost, int receiver) {
        // Create a JPanel and add the buttons
        super(parent, window);

        this.cost = cost;
        this.receiver = receiver;
        loadMain();
    }

    public void loadMain(){
        JPanel currencyExchangePanel = new JPanel();

        currencyExchangePanel.setLayout(new GridLayout(0, 3));
        currencyExchangePanel.setBorder(new EmptyBorder(20, 20, 20, 20));


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
            currencyExchangePanel.add(new JLabel(Double.toString(player.getMIL()) + ". You need $" + Integer.toString(cost)));

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
        btcInputPanel.add(btcField);

        JPanel ethInputPanel = new JPanel();
        ethField = new JTextField(10);
        ethInputPanel.add(ethField);

        currencyExchangePanel.add(ethInputPanel);
        currencyExchangePanel.add(help);
        currencyExchangePanel.add(btcInputPanel);


        // Show purchase buttons
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
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2));

        JButton backButton = new JButton("Finish");
        backButton.addActionListener(this);
        JButton propertyButton = new JButton("Manage Properties");
        propertyButton.addActionListener(this);
        buttonPanel.add(backButton);
        buttonPanel.add(propertyButton);

        mainPanel.removeAll(); // Remove any panels from the previous view
        mainPanel.add(currencyExchangePanel, gbc);
        mainPanel.add(buttonPanel, gbc);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        // Main commands
        if (command.equals("Finish")) {
            if (player.getMIL() > cost){
                player.spendCurrency("MIL", cost);
                if (receiver > -1){
                    game.getPlayers()[receiver].earnCurrency("MIL", cost);
                }
                dispose();
                parent.ctrlComponent.setVisible(true);
            }
        } if (command.equals("Manage Properties")) {
            loadPropertyManager();
        } else if (command.equals("Sell BTC")){
            try{
                player.convertCurrency("BTC", "MIL", Double.parseDouble(btcField.getText()));
                loadMain();
            } catch(Exception e){};
        } else if (command.equals("Sell ETH")){
            try{
                player.convertCurrency("ETH", "MIL", Double.parseDouble(ethField.getText()));
                loadMain();
            } catch(Exception e){};
        }
    }

}
