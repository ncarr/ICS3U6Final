/**
 * [GamePanel.java]
 * Main controller for views
 * @author Nicholas Carr, Carol Chen
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.lang.management.ManagementFactory;

public class GamePanel extends JPanel implements ActionListener {
    private MillennialopolyWindow window;
    protected ControlPanel ctrlComponent;
    private BoardPanel boardComponent;
    private ForceExchangeDialog forceExchangeDialog;
    public Game game;

    public GamePanel(MillennialopolyWindow window, int users, String[] names, Color[] colours) {
        // Create a JPanel and add the buttons
        super();
        this.window = window;

        this.setLayout(new BorderLayout());

        game = new Game(users, names, colours);

        boardComponent = new BoardPanel(this, window);
        this.add(boardComponent); // Add board to window

        // Add the panel to the window
        this.window.add(this);

        // open control panel
        ctrlComponent = new ControlPanel(this, window);
    }

    /**
    * Method called when any button is pressed
    */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
    }

    public void endTurn() {
        Player player = game.getPlayers()[game.getCurrPlayer()];

        if (player == null || player.inJail()){
            game.nextTurn();
            endTurn();
            return;
        }

        ctrlComponent.dispose();

        int roll = Player.roll();
        int newLoc = player.move(roll);
        message(player.getName() + "'s turn!", "You just rolled a " + roll);
        // handle the new location, pay if needed, prompt for purchase if needed
        Tile spot = game.board[newLoc];

        boolean needExchange = false;
        if (spot instanceof Ownable){
            Ownable o = (Ownable)spot;
            int buyCosts = 0; // costs to purchase the tile
            int payCosts = 0; // Costs if the tile is owned
            if (spot instanceof UtilityTile){
                if (o.getOwner() > -1){
                    Player owner = game.getPlayers()[o.getOwner()];
                    if (owner.getNumUtilities() == 1){
                        payCosts = roll * 4;
                    } else {
                        payCosts = roll * 10;
                    }
                }
                buyCosts = UtilityTile.cost;
            } else if (spot instanceof HyperloopTile){
                if (o.getOwner() > -1){
                    Player owner = game.getPlayers()[o.getOwner()];
                    payCosts = HyperloopTile.fares[owner.getNumHyperloops() - 1];
                }
                buyCosts = HyperloopTile.cost;
            } else if (spot instanceof Property){
                payCosts = ((Property)o).getRent();
                buyCosts = ((Property)o).getCost();
            }
            landOnOwnable(o, player, payCosts, buyCosts, newLoc);
        } else {
            if (spot instanceof ChanceTile){
                message(spot.getName(), game.drawCard(((ChanceTile)spot).isWild()));
            } else if (spot instanceof TaxTile){
                int costs = ((TaxTile)spot).getCost();
                if (player.spendCurrency("MIL", costs)) {
                    message("Uh oh, taxes!", "You just paid " + costs);
                    game.addToTaxes(costs);
                } else {
                    if (checkDeath(player, costs)) { // they need to exchange currency to be able to pay
                        ctrlComponent.setVisible(false);
                        forceExchangeDialog(costs, -1);
                        game.addToTaxes(costs);
                    }
                }
            } else if (spot instanceof TheftTile){
                int earnings = game.performCommunism();
                message( "Lucky!", "You just earned " + Integer.toString(earnings) + " in taxes!");
                player.earnCurrency("MIL", earnings);
            } else if (spot instanceof GoToJailTile) {
                player.goToJail();
                message( "Uh oh!", "You're going to jail!");
            }
            ctrlComponent = new ControlPanel(this, window);
        }
        boardComponent.refresh();
    }

    // returns 0 if no exchange needed, 1 if for purchase and 2 if for
    public void landOnOwnable(Ownable o, Player player, int payCosts, int buyCosts, int loc){
        ctrlComponent = new ControlPanel(this, window);
        ctrlComponent.setVisible(false);
        boolean forceExchange = false;
        if (o.getOwner() != -1){ // there is an owner, then rent should be paid
            if (!(o.getOwner() == game.getCurrPlayer())){ // make sure it's not owned by the user
                Player owner = game.getPlayers()[o.getOwner()];
                payToMessage(payCosts, owner.getName());
                if (checkDeath(player, payCosts)) { // they need to exchange currency to be able to pay
                    if (player.spendCurrency("MIL", payCosts)) {
                        message("Rippy dippy", "You just paid " + payCosts);
                    } else {
                        forceExchangeDialog(payCosts, 1);
                        forceExchange = true;
                        player.spendCurrency("MIL", payCosts);
                    }
                    owner.earnCurrency("MIL", payCosts);
                }
            }
        } else { // Then they can buy!
            if (player.getAssetTotal() <= buyCosts){
                message("Sorry", "Not enough money to buy this!");
            } else {
                int choice = buyOption(buyCosts, o.getName());

                if (choice == 0){
                    if (checkDeath(player, buyCosts)) { // they need to exchange currency to be able to pay
                        if (player.spendCurrency("MIL", buyCosts)) {
                            message( "Yay!", "You just paid " + buyCosts);
                            game.sellOwnable(loc);
                        } else {
                            forceExchangeDialog(buyCosts, -1);
                            forceExchangeDialog.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    game.sellOwnable(loc);
                                }
                            });
                            forceExchange = true;
                        }
                    }
                }
            }
        }
        if (!forceExchange){
            ctrlComponent.setVisible(true);
        }
    }

    private boolean checkDeath(Player player, int costs){
        if (player.getAssetTotal() <= costs){
            lose();
            return false;
        }
        return true;
    }

    private void lose(){
        loadLoseDialog();
        if (game.killCurrPlayer()){
            for (Player p: game.getPlayers()){
                if (p != null){
                    message("Game over!", p.getName() + " is victorious. Everyone else lost all their money rip. ");
                }
            }
            try {
                StringBuilder cmd = new StringBuilder();
                cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
                for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
                    cmd.append(jvmArg + " ");
                }
                cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
                cmd.append(Millennialopoly.class.getName()).append(" ");
                Runtime.getRuntime().exec(cmd.toString());
                System.exit(0);
            } catch (Exception e){}
        } else {
            game.nextTurn();
            endTurn();
        }

    }

    public void forceExchangeDialog(int cost, int receiver){
        forceExchangeDialog = new ForceExchangeDialog(this, window, cost, receiver);
    }

    private int buyOption(int costs, String name){
        Object[] options =  {"Buy!", "Don't Buy"};
        return JOptionPane.showOptionDialog(
            window,
            "Would you like to buy " + name + " for " + costs + "MIL?",
            "This property can be purchased!",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
    }

    private void payToMessage(int costs, String name){
        message( "Pay up!", "You need to pay " + costs + " to " + name);
    }

    private void loadLoseDialog(){
        message("Die!", "YOU LOST HAHAHHA");
    }

    private void message(String title, String message){
        JOptionPane.showMessageDialog(
            null,
            message, title,
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
