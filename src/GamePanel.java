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
        JOptionPane.showMessageDialog(null, "You just rolled a " + roll, player.getName() + "'s turn!", JOptionPane.INFORMATION_MESSAGE);
        // handle the new location, pay if needed, prompt for purchase if needed
        Tile spot = game.board.tiles[newLoc];

        boolean needExchange = false;
        int costs = 0;
        if (spot instanceof ChanceTile){
            // draw card
        } else if (spot instanceof TaxTile){
            costs = ((TaxTile)spot).getCost();
            if (player.spendCurrency("MIL", costs)) {
                JOptionPane.showMessageDialog(null, "You just paid " + costs, "Uh oh, taxes!", JOptionPane.INFORMATION_MESSAGE);
                game.addToTaxes(costs);
            } else {
                if (player.getCurrencyTotal() < costs + 10){ // Approximate death
                    player.lose();
                    loadLoseDialog();
                } else { // they need to exchang currency to be able to pay
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
                JOptionPane.showMessageDialog(null, "You need to pay " + costs + " to " + owner.getName(), "Pay up!", JOptionPane.INFORMATION_MESSAGE);
                if (player.getAssetTotal() < costs){
                    player.lose();
                    loadLoseDialog();
                } else { // they need to exchang currency to be able to pay
                    if (player.spendCurrency("MIL", costs)) {
                        JOptionPane.showMessageDialog(null, "You just paid " + costs, "Rippy dippy", JOptionPane.INFORMATION_MESSAGE);
                        owner.earnCurrency("MIL", costs);
                    } else {
                        needExchange = true;
                        player.spendCurrency("MIL", costs);
                        owner.earnCurrency("MIL", costs);
                    }
                }
            } else {
                costs = HyperloopTile.cost;
                Object[] options = {"Buy!", "Don't Buy"};
                int choice = JOptionPane.showOptionDialog(window,
                    "Would you like to buy " + h.getName() + " for " + costs + "MIL?",
                    "This property can be purchased!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                if (choice == 0){
                    if (player.getAssetTotal() < costs){
                        player.lose();
                        loadLoseDialog();
                    } else { // they need to exchang currency to be able to pay
                        if (player.spendCurrency("MIL", costs)) {
                            JOptionPane.showMessageDialog(null, "You just paid " + costs, "Yay!", JOptionPane.INFORMATION_MESSAGE);
                            game.sellHyperloop(newLoc);
                        } else {
                            needExchange = true;
                            game.sellHyperloop(newLoc);
                        }
                    }
                }
            }
        } else if (spot instanceof Property){
            Property p = (Property)spot;
            if (p.getOwner() > -1){ // there is an owner, then rent should be paid
                costs = p.getRent();
                Player owner = game.getPlayers()[p.getOwner()];
                JOptionPane.showMessageDialog(null, "You need to pay " + costs + " to " + owner.getName(), "Pay up!", JOptionPane.INFORMATION_MESSAGE);
                if (player.getAssetTotal() < costs){
                    player.lose();
                    loadLoseDialog();
                } else { // they need to exchang currency to be able to pay
                    if (player.spendCurrency("MIL", costs)) {
                        JOptionPane.showMessageDialog(null, "You just paid " + costs, "Rippy dippy", JOptionPane.INFORMATION_MESSAGE);
                        owner.earnCurrency("MIL", costs);
                    } else {
                        needExchange = true;
                        player.spendCurrency("MIL", costs);
                        owner.earnCurrency("MIL", costs);
                    }
                }
            } else {
                costs = p.getCost();
                Object[] options = {"Buy!", "Don't Buy"};
                int choice = JOptionPane.showOptionDialog(window,
                    "Would you like to buy " + p.getName() + " for " + costs + "MIL?",
                    "This property can be purchased!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                if (choice == 0){
                    if (player.getAssetTotal() < costs){
                        player.lose();
                        loadLoseDialog();
                    } else { // they need to exchang currency to be able to pay
                        if (player.spendCurrency("MIL", costs)) {
                            JOptionPane.showMessageDialog(null, "You just paid " + costs, "Yay!", JOptionPane.INFORMATION_MESSAGE);
                            game.sellProperty(newLoc);
                        } else {
                            needExchange = true;
                            game.sellProperty(newLoc);
                        }
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

    public void loadLoseDialog(){
        JOptionPane.showMessageDialog(null, "YOU LOST HAHAHHA", "Die!", JOptionPane.INFORMATION_MESSAGE);
        // Do the thing so that the spots are no longer owned by that player
    }

    public void forceExchangeDialog(int cost, int receiver){
        forceExchangeDialog = new ForceExchangeDialog(this, window, cost, receiver);
        forceExchangeDialog.setModal(true);
        forceExchangeDialog.setVisible(true);
    }
}
