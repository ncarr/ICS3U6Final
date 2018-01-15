/**
 * [PropertyManager.java]
 * View for looking at properties,
 * @author Nicholas Carr, Carol Chen
 */

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

public class PropertyManager extends JPanel implements ActionListener {

    private MillenialopolyWindow window;
    private GridBagConstraints gbc;

    private MillenialopolyDialog parent;
    private Game game;
    private Player player;

    private JTextField ethField;
    private JTextField btcField;

    private int cost, receiver; // the amount that needs to be paid an the index of the receiver, -1 if none

    public PropertyManager(MillenialopolyDialog parent, MillenialopolyWindow window) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;
        this.parent = parent;
        this.game = parent.game;
        this.player = game.getPlayers()[game.getCurrPlayer()];

        this.cost = cost;
        this.receiver = receiver;

        this.setLayout(new GridBagLayout());

        JPanel propertyManagePanel = new JPanel();

        propertyManagePanel.setLayout(new GridLayout(0, 2));

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int idx = 0;
        for (Property p: player.getProperties()){
            JPanel propertyPanel = new JPanel();
            propertyPanel.setLayout(new GridBagLayout());
            propertyPanel.setBorder(new EmptyBorder(10, 0, 25, 0));

            JPanel titlePanel = new JPanel();
            Font titleFont = Fonts.TITLE.deriveFont(25F);
            JLabel titleLabel = new JLabel(p.getName(), SwingConstants.CENTER);
            titleLabel.setFont(titleFont);
            titlePanel.setBackground(p.getColor());
            if (p.isMortgaged()){
                titlePanel.add(new JLabel("(mortgaged)"));
            }
            titlePanel.add(titleLabel);

            JLabel rentLabel = new JLabel("rent", SwingConstants.CENTER);

            JPanel avocadoPanel = new JPanel();
            avocadoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            avocadoPanel.setLayout(new GridLayout(4, 2));
            for (int i = 0; i < 4; i++){
                avocadoPanel.add(new JLabel("With " + Integer.toString(i)+ " avocado"));
                avocadoPanel.add(new JLabel("$" + Integer.toString(p.getRentVals()[i]) + " ", SwingConstants.RIGHT));
            }

            JLabel mortgageLabel = new JLabel("Mortgage value: $" + Integer.toString(p.getMortgage()), SwingConstants.CENTER);
            JLabel avocadoCostLabel = new JLabel("Avocado cost: $" + Integer.toString(p.getAvocadoCost()), SwingConstants.CENTER);


            propertyPanel.add(titlePanel, gbc);
            propertyPanel.add(rentLabel, gbc);
            propertyPanel.add(avocadoPanel, gbc);
            propertyPanel.add(mortgageLabel, gbc);
            propertyPanel.add(avocadoCostLabel, gbc);

            if (p.getAvocados() > 0){
                JButton sellAvocadoButton = new JButton("Sell Avocado");
                sellAvocadoButton.putClientProperty("index", idx);
                sellAvocadoButton.addActionListener(this);
                propertyPanel.add(sellAvocadoButton, gbc);
            } else {
                JButton mortgageButton;
                if (p.isMortgaged()){
                    mortgageButton = new JButton("Unmortgage");
                } else {
                    mortgageButton = new JButton("Mortgage");
                }
                mortgageButton.putClientProperty("index", idx);
                mortgageButton.addActionListener(this);
                propertyPanel.add(mortgageButton, gbc);
            }

            if (player.canBuyAvocados(p) && !p.isMortgaged()){
                JButton buyAvocadoButton = new JButton("Buy Avocado");
                buyAvocadoButton.putClientProperty("index", idx);
                buyAvocadoButton.addActionListener(this);
                propertyPanel.add(buyAvocadoButton, gbc);
            }

            propertyManagePanel.add(propertyPanel);
            idx++;
        }

        JScrollPane scrollPane = new JScrollPane(propertyManagePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(window.getBounds().width - 600, window.getBounds().height - 300));

        this.add(scrollPane, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);

        this.add(backButton, gbc);
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        JButton btn = (JButton) event.getSource();
        if (command.equals("Back")) {
            parent.loadMain();
        } else if (command.equals("Mortgage") || command.equals("Unmortgage")){
            int i = (Integer)btn.getClientProperty("index");
            boolean spent = player.getProperties().get(i).changeMortgage();
            if (spent){
                player.earnCurrency("MIL", player.getProperties().get(i).getMortgage());
            } else {
                player.spendCurrency("MIL", player.getProperties().get(i).getMortgage());
            }
            parent.loadPropertyManager();
        } else if (command.equals("Sell Avocado")){
            int i = (Integer)btn.getClientProperty("index");
            player.getProperties().get(i).sellAvocado();
            player.earnCurrency("MIL", player.getProperties().get(i).getAvocadoCost());
            parent.loadPropertyManager();
        } else if (command.equals("Buy Avocado")){
            int i = (Integer)btn.getClientProperty("index");
            player.getProperties().get(i).addAvocado();
            player.spendCurrency("MIL", player.getProperties().get(i).getAvocadoCost());
            parent.loadPropertyManager();
        }
    }
}
