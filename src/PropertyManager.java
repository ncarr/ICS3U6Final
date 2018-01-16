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

import java.util.*;
import java.io.*;

public class PropertyManager extends JPanel implements ActionListener {

    private MillennialopolyWindow window;
    private GridBagConstraints gbc;

    private MillennialopolyDialog parent;
    private Game game;
    private Player player;

    private JTextField ethField;
    private JTextField btcField;

    private int cost, receiver; // the amount that needs to be paid an the index of the receiver, -1 if none

    public PropertyManager(MillennialopolyDialog parent, MillennialopolyWindow window) {
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
        Collections.sort(player.getOwnables());
        for (Ownable p: player.getOwnables()){
            JPanel propertyPanel = new JPanel();
            propertyPanel.setLayout(new GridBagLayout());
            propertyPanel.setBorder(new EmptyBorder(10, 0, 25, 0));

            JPanel titlePanel = new JPanel();
            Font titleFont = Fonts.TITLE.deriveFont(25F);
            JLabel titleLabel = new JLabel(p.getName(), SwingConstants.CENTER);
            titleLabel.setFont(titleFont);
            if (p instanceof Property){titlePanel.setBackground(((Property)p).getColor());}
            if (p.isMortgaged()){
                titlePanel.add(new JLabel("(mortgaged)"));
            }
            titlePanel.add(titleLabel);
            propertyPanel.add(titlePanel, gbc);

            if (p instanceof Property){
                Property prop = (Property)p;
                JLabel rentLabel = new JLabel(Integer.toString(prop.getRentVals()[0]),
                                              SwingConstants.CENTER);

                JPanel avocadoPanel = new JPanel();
                avocadoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                avocadoPanel.setLayout(new GridLayout(5, 2));
                for (int i = 1; i < 6; i++){
                    avocadoPanel.add(new JLabel("With " + Integer.toString(i)+ " avocado"));
                    avocadoPanel.add(new JLabel("$" + Integer.toString(prop.getRentVals()[i]) + " ", SwingConstants.RIGHT));
                }

                JLabel avocadoCostLabel = new JLabel("Avocado cost: $" + Integer.toString(prop.getAvocadoCost()),
                                                     SwingConstants.CENTER);

                propertyPanel.add(rentLabel, gbc);
                propertyPanel.add(avocadoPanel, gbc);
                propertyPanel.add(avocadoCostLabel, gbc);
            } else if (p instanceof UtilityTile){
                JLabel rentLabel = new JLabel("If one Utility is owned, then rent is 4 times the die roll.", SwingConstants.CENTER);
                JLabel rentLabel2 = new JLabel("If two Utilities are owned, then retn is 10 times the die roll.", SwingConstants.CENTER);
                propertyPanel.add(rentLabel, gbc);
                propertyPanel.add(rentLabel2, gbc);
            } else if (p instanceof HyperloopTile){
                JPanel rentPanel = new JPanel();
                rentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                rentPanel.setLayout(new GridLayout(4, 2));
                for (int i = 0; i < 4; i++){
                    rentPanel.add(new JLabel("With " + Integer.toString(i)+ " Hyperloop owned "));
                    rentPanel.add(new JLabel("$" + Integer.toString(HyperloopTile.fares[i]) + " ", SwingConstants.RIGHT));
                }
                propertyPanel.add(rentPanel, gbc);
            }

            JLabel mortgageLabel = new JLabel("Mortgage value: $" + Integer.toString(p.getMortgage()), SwingConstants.CENTER);
            propertyPanel.add(mortgageLabel, gbc);


            if (p instanceof Property && ((Property)p).getAvocados() > 0){
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
                if (player.getMIL() >= p.getMortgage() && p.isMortgaged() || !p.isMortgaged()){
                    propertyPanel.add(mortgageButton, gbc);
                }
            }

            if (p instanceof Property &&
                player.canBuyAvocados((Property)p) &&
                !((Property)p).isMortgaged() &&
                player.getMIL() >= ((Property)p).getAvocadoCost()){
                JButton buyAvocadoButton = new JButton("Buy Avocado");
                buyAvocadoButton.putClientProperty("index", idx);
                buyAvocadoButton.addActionListener(this);
                propertyPanel.add(buyAvocadoButton, gbc);
            }

            propertyManagePanel.add(propertyPanel);
            idx++;
        }

        JScrollPane scrollPane = new JScrollPane(propertyManagePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(window.getBounds().width - 400, window.getBounds().height - 300));

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
            boolean spent = player.getOwnables().get(i).changeMortgage();
            if (spent){
                player.spendCurrency("MIL", player.getOwnables().get(i).getMortgage());
            } else {
                player.earnCurrency("MIL", player.getOwnables().get(i).getMortgage());
            }
            parent.loadPropertyManager();
        } else if (command.equals("Sell Avocado")){
            int i = (Integer)btn.getClientProperty("index");
            player.getProperties().get(i).sellAvocado();
            player.earnCurrency("MIL", ((Property)player.getOwnables().get(i)).getAvocadoCost());
            parent.loadPropertyManager();
        } else if (command.equals("Buy Avocado")){
            int i = (Integer)btn.getClientProperty("index");
            player.getProperties().get(i).addAvocado();
            player.spendCurrency("MIL", ((Property)player.getOwnables().get(i)).getAvocadoCost());
            parent.loadPropertyManager();
        }
    }
}
