/**
 * [ControlPanel.java]
 * This panel shows up for users to manage properties and currencies after making their move
 * @author Nicholas Carr, Carol Chen
 */

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

public class ControlPanel extends MillenialopolyDialog implements ActionListener {

    // Fields for currency exchange
    private JTextField ethField;
    private JTextField btcField;

    public ControlPanel(GamePanel parent, MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super(parent, window);
        loadMain();

    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        // Main commands
        if (command.equals("End Turn")) {
            parent.endTurn();
            dispose();
        } else if (command.equals("Exchange Currencies")){
            loadCurrencyExchange();
        } else if (command.equals("Manage Properties")){
            loadPropertyManager();
        } else if (command.equals("Back")){
            mainPanel.removeAll(); // Remove any panels from the previous view
            loadMain();
        }

        // Exchange commands
        else if (command.equals("Buy BTC")) {
            try{ // try for number porsing
                player.convertCurrency("MIL", "BTC", Double.parseDouble(btcField.getText()));
                loadCurrencyExchange();
            } catch(Exception e){};
        } else if (command.equals("Sell BTC")){
            try{
                player.convertCurrency("BTC", "MIL", Double.parseDouble(btcField.getText()));
                loadCurrencyExchange();
            } catch(Exception e){};
        } else if (command.equals("Buy ETH")){
            try{
                player.convertCurrency("MIL", "ETH", Double.parseDouble(ethField.getText()));
                loadCurrencyExchange();
            } catch(Exception e){};
        } else if (command.equals("Sell ETH")){
            try{
                player.convertCurrency("ETH", "MIL", Double.parseDouble(ethField.getText()));
                loadCurrencyExchange();
            } catch(Exception e){};
        }

    }

    // Start all the formatting fun!
    public void loadMain(){ // Main contains the base view

        // Title, which is also the name
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        Font titleFont = Fonts.TITLE.deriveFont(50F);
        JLabel titleLabel = new JLabel(player.getName(),
                                       SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(player.getColour());
        titlePanel.add(titleLabel);

        // Display currency in a grid layout
        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new GridLayout(0, 3));
        currencyPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add images
        try{
            Image ethImage = ImageIO.read(new File("img/ethereum.png"));
            ethImage = ethImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            JLabel ethImageLabel = new JLabel(new ImageIcon(ethImage));

            Image milImage = ImageIO.read(new File("img/mil.png"));
            milImage = milImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            JLabel milImageLabel = new JLabel(new ImageIcon(milImage));

            Image btcImage = ImageIO.read(new File("img/bitcoin.png"));
            btcImage = btcImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            JLabel btcImageLabel = new JLabel(new ImageIcon(btcImage));

            currencyPanel.add(ethImageLabel);
            currencyPanel.add(milImageLabel);
            currencyPanel.add(btcImageLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Show owned currency
        JLabel btcLabel = new JLabel(Double.toString(player.getBTC()), SwingConstants.CENTER);
        JLabel milLabel = new JLabel(Double.toString(player.getMIL()), SwingConstants.CENTER);
        JLabel ethLabel = new JLabel(Double.toString(player.getETH()), SwingConstants.CENTER);
        currencyPanel.add(ethLabel);
        currencyPanel.add(milLabel);
        currencyPanel.add(btcLabel);

        // button options
        JPanel buttonPanel = new JPanel();
        JButton tradeButton = new JButton("Offer a Trade");
        JButton currencyButton = new JButton("Exchange Currencies");
        JButton propertyButton = new JButton("Manage Properties");
        JButton endButton = new JButton("End Turn");

        // Add action listeners to the buttons
        tradeButton.addActionListener(this);
        currencyButton.addActionListener(this);
        propertyButton.addActionListener(this);
        endButton.addActionListener(this);

        // Add the buttons to the JPanel
        buttonPanel.add(tradeButton);
        buttonPanel.add(currencyButton);
        buttonPanel.add(propertyButton);
        buttonPanel.add(endButton);

        mainPanel.removeAll(); // Remove any panels from the previous view
        mainPanel.add(titlePanel, gbc);
        mainPanel.add(currencyPanel, gbc);
        mainPanel.add(buttonPanel, gbc);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void loadCurrencyExchange(){ // loaded when user wants to exchange currency
        JPanel currencyExchangePanel = new JPanel();

        currencyExchangePanel.setLayout(new GridLayout(0, 3));
        currencyExchangePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // How much money they have in total
        currencyExchangePanel.add(new JLabel()); // Empty panel to padd
        currencyExchangePanel.add(new JLabel("You have " + Double.toString(player.getCurrencyTotal()) + " MIL in total"), SwingConstants.CENTER);
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
        JLabel btcLabel = new JLabel(Double.toString(player.getBTC()),
                                     SwingConstants.CENTER);
        JLabel ethLabel = new JLabel(Double.toString(player.getETH()),
                                     SwingConstants.CENTER);
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
        JButton buyETHButton = new JButton("Buy ETH");
        JButton sellETHButton = new JButton("Sell ETH");
        buyETHButton.addActionListener(this);
        sellETHButton.addActionListener(this);
        buttonETHPanel.add(buyETHButton);
        buttonETHPanel.add(sellETHButton);

        JPanel buttonBTCPanel = new JPanel();
        JButton buyBTCButton = new JButton("Buy BTC");
        JButton sellBTCButton = new JButton("Sell BTC");
        buyBTCButton.addActionListener(this);
        sellBTCButton.addActionListener(this);
        buttonBTCPanel.add(buyBTCButton);
        buttonBTCPanel.add(sellBTCButton);
        // Add buttons to panel
        currencyExchangePanel.add(buttonETHPanel);
        currencyExchangePanel.add(new JPanel());
        currencyExchangePanel.add(buttonBTCPanel);

        // Add back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);

        mainPanel.removeAll(); // Remove any panels from the previous view
        mainPanel.add(currencyExchangePanel, gbc);
        mainPanel.add(backButton, gbc);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
