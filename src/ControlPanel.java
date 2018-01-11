import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;
/**
 * Panel that contains options for users to take during a move
 * @author Nicholas Carr, Carol Chen
 */
public class ControlPanel extends JDialog implements ActionListener {
    private MillenialopolyWindow window;
    private GridBagConstraints gbc;
    private JPanel mainPanel;

    private GamePanel parent;
    private Game game;
    private Player player;

    public ControlPanel(GamePanel parent, MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;
        this.parent = parent;
        this.game = parent.game;
        this.player = game.getPlayers()[game.getCurrPlayer()];

        Rectangle r = window.getBounds();
        this.setSize(r.width - 250, r.height - 200);  // Don't have it take up full width
        this.setLocationRelativeTo(window);

        this.setUndecorated(true); // No standard dialog decorations
        this.setVisible(true);
        this.setAlwaysOnTop(true); // Make sure that it's always on top

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints(); // Constraints
        gbc.gridx = 1; // One column per row
        gbc.fill = GridBagConstraints.HORIZONTAL; // Have each item fill the row

        this.add(mainPanel);
        loadMain();
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("End Turn")) {
            parent.endTurn();
            dispose();
        } else if (command.equals("Exchange Currencies")){
            loadCurrencyExchange();
        } else if (command.equals("Buy Avocadoes")){

        } else if (command.equals("Back")){
            System.out.println("REEEEE");
            mainPanel.removeAll(); // Remove any panels from the previous view
            loadMain();
        }
    }

    public void loadMain(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        Font titleFont = Fonts.TITLE.deriveFont(50F);
        JLabel titleLabel = new JLabel(player.getName(),
                                       SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);


        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new GridLayout(0, 3));
        currencyPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
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

        JLabel btcLabel = new JLabel(Double.toString(player.getBTC()), SwingConstants.CENTER);
        JLabel milLabel = new JLabel(Double.toString(player.getMIL()), SwingConstants.CENTER);
        JLabel ethLabel = new JLabel(Double.toString(player.getETH()), SwingConstants.CENTER);
        currencyPanel.add(btcLabel);
        currencyPanel.add(milLabel);
        currencyPanel.add(ethLabel);

        // button options
        JPanel buttonPanel = new JPanel();
        JButton tradeButton = new JButton("Offer a Trade");
        JButton currencyButton = new JButton("Exchange Currencies");
        JButton avocadoButton = new JButton("Buy Avocadoes");
        JButton endButton = new JButton("End Turn");

        // Add action listeners to the buttons
        tradeButton.addActionListener(this);
        currencyButton.addActionListener(this);
        avocadoButton.addActionListener(this);
        endButton.addActionListener(this);

        // Add the buttons to the JPanel
        buttonPanel.add(tradeButton);
        buttonPanel.add(currencyButton);
        buttonPanel.add(avocadoButton);
        buttonPanel.add(endButton);

        mainPanel.removeAll(); // Remove any panels from the previous view
        mainPanel.add(titlePanel, gbc);
        mainPanel.add(currencyPanel, gbc);
        mainPanel.add(buttonPanel, gbc);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void loadCurrencyExchange(){
        JPanel currencyExchangePanel = new JPanel();
        currencyExchangePanel.setLayout(new GridLayout(0, 3));
        currencyExchangePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        try{
            Image milImage = ImageIO.read(new File("img/mil.png"));
            milImage = milImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            JLabel milImageLabel = new JLabel(new ImageIcon(milImage));

            currencyExchangePanel.add(new JPanel()); // Empty panel to padd
            currencyExchangePanel.add(milImageLabel);
            currencyExchangePanel.add(new JPanel()); // empty panel
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel btcExchangeLabel = new JLabel(Double.toString(game.Ethereum.getValInMIL()) + " MIL", SwingConstants.CENTER);
        JLabel ethExchangeLabel = new JLabel(Double.toString(game.Bitcoin.getValInMIL()) + " MIL", SwingConstants.CENTER);
        currencyExchangePanel.add(btcExchangeLabel);
        currencyExchangePanel.add(new JLabel());
        currencyExchangePanel.add(ethExchangeLabel);

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

        JLabel btcLabel = new JLabel(Double.toString(player.getBTC()), SwingConstants.CENTER);
        JLabel ethLabel = new JLabel(Double.toString(player.getETH()), SwingConstants.CENTER);
        currencyExchangePanel.add(btcLabel);
        currencyExchangePanel.add(new JLabel());
        currencyExchangePanel.add(ethLabel);


        // button options
        JPanel buttonETHPanel = new JPanel();
        JButton buyETHButton = new JButton("Buy");
        JButton sellETHButton = new JButton("Sell");
        buyETHButton.addActionListener(this);
        sellETHButton.addActionListener(this);
        buttonETHPanel.add(buyETHButton);
        buttonETHPanel.add(sellETHButton);

        JPanel buttonBTCPanel = new JPanel();
        JButton buyBTCButton = new JButton("Buy");
        JButton sellBTCButton = new JButton("Sell");
        buyBTCButton.addActionListener(this);
        sellBTCButton.addActionListener(this);
        buttonBTCPanel.add(buyBTCButton);
        buttonBTCPanel.add(sellBTCButton);

        currencyExchangePanel.add(buttonETHPanel);
        currencyExchangePanel.add(new JPanel());
        currencyExchangePanel.add(buttonBTCPanel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        currencyExchangePanel.add(new JPanel());
        currencyExchangePanel.add(backButton);
        currencyExchangePanel.add(new JPanel());

        mainPanel.removeAll(); // Remove any panels from the previous view
        mainPanel.add(currencyExchangePanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
