/**
 * [GamePanel.java]
 * Main controller for views
 * @author Nicholas Carr, Carol Chen
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private MillenialopolyWindow window;
    private ControlPanel ctrlComponent;
    private BoardPanel boardComponent;
    private ForceExchangeDialog forceExchangeDialog;
    public Game game;

    public GamePanel(MillenialopolyWindow window, int users, String[] names, Color[] colours) {
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
        game.nextTurn();
        ctrlComponent.dispose();
        int roll = Player.roll();
        int newLoc = player.move(roll);
        message(player.getName() + "'s turn!", "You just rolled a " + roll);
        // handle the new location, pay if needed, prompt for purchase if needed
        Tile spot = game.board[newLoc];

        boolean needExchange = false;
        int costs = 0;
        if (spot instanceof ChanceTile){
            // draw card
        } else if (spot instanceof TaxTile){
            costs = ((TaxTile)spot).getCost();
            if (player.spendCurrency("MIL", costs)) {
                message("Uh oh, taxes!", "You just paid " + costs);
                game.addToTaxes(costs);
            } else {
                if (player.getCurrencyTotal() < costs + 10){ // Approximate death
                    lose(player);
                } else { // they need to exchange currency to be able to pay
                    needExchange = true;
                    game.addToTaxes(costs);
                }
            }
        } else if (spot instanceof TheftTile){
            player.earnCurrency("MIL", game.performCommunism());
        } else if (spot instanceof HyperloopTile){
            HyperloopTile h = (HyperloopTile)spot;
            if (h.getOwner() > -1){ // there is an owner, then rent should be paid
                costs = HyperloopTile.fares[player.getHyperloops()];
                Player owner = game.getPlayers()[h.getOwner()];

                payToMessage(costs, owner.getName());
                if (player.getAssetTotal() < costs){
                    lose(player);
                } else { // they need to exchang currency to be able to pay
                    if (player.spendCurrency("MIL", costs)) {
                        message("Rippy dippy", "You just paid " + costs);
                    } else {
                        needExchange = true;
                        player.spendCurrency("MIL", costs);
                    }
                    owner.earnCurrency("MIL", costs);
                }
            } else {
                costs = HyperloopTile.cost;
                int choice = buyOption(costs, h.getName());

                if (choice == 0){
                    if (player.getAssetTotal() < costs){
                        lose(player);
                    } else { // they need to exchang currency to be able to pay
                        if (player.spendCurrency("MIL", costs)) {
                            message( "Yay!", "You just paid " + costs);
                        } else {
                            needExchange = true;
                        }
                        game.sellHyperloop(newLoc);
                    }
                }
            }
        } else if (spot instanceof Property){
            Property p = (Property)spot;

            if (p.getOwner() > -1){ // there is an owner, then rent should be paid
                costs = p.getRent();
                Player owner = game.getPlayers()[p.getOwner()];

                payToMessage(costs, owner.getName());
                if (player.getAssetTotal() < costs){
                    lose(player);
                } else { // they need to exchange currency to be able to pay
                    if (player.spendCurrency("MIL", costs)) {
                        message("Rippy dippy", "You just paid " + costs);
                    } else {
                        needExchange = true;
                        player.spendCurrency("MIL", costs);
                    }
                    owner.earnCurrency("MIL", costs);
                }
            } else { // give option to purchase the property
                costs = p.getCost();
                int choice = buyOption(costs, p.getName());

                if (choice == 0){
                    if (player.getAssetTotal() < costs){
                        lose(player);
                    } else { // they need to exchange currency to be able to pay
                        if (player.spendCurrency("MIL", costs)) { // if enough money
                            message("Yay!", "You just paid " + costs);
                        } else { // if not  enough money
                            needExchange = true;
                        }
                        game.sellProperty(newLoc);
                    }
                }
            }
        }
        ctrlComponent = new ControlPanel(this, window);
        if (needExchange){
            forceExchangeDialog(costs, -1);
        }
        boardComponent.refresh();
    }

    private void lose(Player player){
        player.lose();
        loadLoseDialog();
    }

    public void forceExchangeDialog(int cost, int receiver){
        forceExchangeDialog = new ForceExchangeDialog(this, window, cost, receiver);
    }

    public int buyOption(int costs, String name){
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

    public void payToMessage(int costs, String name){
        message( "Pay up!", "You need to pay " + costs + " to " + name);
    }

    public void loadLoseDialog(){
        message("Die!", "YOU LOST HAHAHHA");
    }

    public void message(String title, String message){
        JOptionPane.showMessageDialog(
            null,
            "YOU LOST HAHAHHA", "Die!",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
