/**
 * [Player.java]
 * Player class
 * @author Nicholas Carr, Carol Chen, Nathan Shen
 */


import java.util.*;
import java.awt.Color;

public class Player{// Player data
// Variables
    private String name;
    private ArrayList<Property> properties;//properties owned
    private ArrayList<HyperloopTile> hyperloops;//hyperloops owned
    private ArrayList<UtilityTile> utilities;//utilities owned
    private int location;
    private double MIL, BTC, ETH;// Wealth
    private Color colour;
    private int movementFactor;
    private int jailTermsLeft;

    // Constructs player ---------------------------------------------------------------------------------------
    Player(String playerName, Color colour) {
        name = playerName;
        this.colour = colour;
        location = 0; // start at go
        properties = new ArrayList<Property>();
        hyperloops = new ArrayList<HyperloopTile>();
        utilities = new ArrayList<UtilityTile>();
        MIL = 1500; // Starting money
        movementFactor = 1; // Some wild chance cards will adjust this to have you move backwards
        jailTermsLeft = -1;
    }

// movement/dice roll for player ---------------------------------------------------------------------------------------
    public int move(int roll) {
        if (!inJail()) {
            location += roll * movementFactor;
            if (location >= 40 || location < 0){
                location = Math.floorMod(location, 40);
                passGo();
            }
        }
        return location;
    }
//---------------------------------------------------------------------------------------------------------------------
  // pass go
    private void passGo(){
        earnCurrency("MIL", 200);
    }

    public double getAssetTotal(){
        return getPropertyValue() + getCurrencyTotal();
    }

    // To get the value of selling avocadoes
    public int getPropertyValue(){
        int res = 0;
        for (Property p: properties){
            if (!p.isMortgaged()){
                res += p.getMortgage();
            }
            if (p.getAvocados() > 0){
                res += p.getAvocadoCost() * p.getAvocados();
            }
        }
        return res;
    }

    // find total wealth of all 3 currencies
    public double getCurrencyTotal(){
        double total = 0;
        total += MIL;
        total += ETH * Game.Ethereum.getFactorRelToMIL();
        total += BTC * Game.Bitcoin.getFactorRelToMIL();
        return total;
    }

    // To spend a currency, return False if not possible
    public boolean spendCurrency(String currency, double amountMIL){
        if (currency.equals("ETH")){
            if (Game.Ethereum.convertFromMIL(amountMIL) > ETH){
                return false;
            }
            ETH -= Game.Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            if (Game.Bitcoin.convertFromMIL(amountMIL) > BTC){
                return false;
            }
            BTC -= Game.Bitcoin.convertFromMIL(amountMIL);
        } else if (currency.equals("MIL")){
            if (MIL < amountMIL){
                return false;
            }
            MIL -= amountMIL;
        }

        return true;
    }

    // makes player earn currency
    public void earnCurrency(String currency, double amountMIL){
        if (currency.equals("ETH")){
            ETH += Game.Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            BTC += Game.Bitcoin.convertFromMIL(amountMIL);
        } else if (currency.equals("MIL")){
            MIL += amountMIL;
        }
    }

    // To convert a currency, with the amount being the amount spent---------------------------------------------------------------------------------------
    public boolean convertCurrency(String fromCurr, String toCurr, double amount){

        if (!spendCurrency(fromCurr, amount)){
            return false;
        }

        if (fromCurr.equals("ETH")){
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, amount);
            }
        } else if (fromCurr.equals("BTC")){
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, amount);
            }
        } else if (fromCurr.equals("MIL")){
            if (toCurr.equals("ETH")){
                earnCurrency(toCurr, amount);
            }
            if (toCurr.equals("BTC")){
                earnCurrency(toCurr, amount);
            }
        }
        return true;
    }
//---------------------------------------------------------------------------------------
   
    // checks if player can buy avocadoes ---------------------------------------------------------------------------------------
    public boolean canBuyAvocados(Property p){
        if (p.getAvocados() < 6){
            MillennialopolyColor col = p.getColor();
            int count = 0;
            for (Property prop: getProperties()){
                if (prop.getColor().equals(col)){
                    count++;
                }
            }
            if (col.equals(MillennialopolyColor.brown)){
                if (count == 2) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (count == 3){
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
   // ---------------------------------------------------------------------------------------
    // player in jail +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void nextTurn() {
        if (inJail()) {
            jailTermsLeft--;
        }
    }
    public boolean inJail() {
        return jailTermsLeft >= 0;
    }
    public void goToJail() {
        this.location = 10;
        this.jailTermsLeft = 3;
    }
    public void goBack() {
        this.movementFactor = -1;
    }
 //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //buying tiles [[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[
    public void addProperty(Property p){
        properties.add(p);
    }

    public void addHyperloop(HyperloopTile h){
        hyperloops.add(h);
    }

    public void addUtility(UtilityTile u){
        utilities.add(u);
    }

    public int getNumHyperloops(){
        return hyperloops.size();
    }

    public int getNumUtilities(){
        return utilities.size();
    }

    public ArrayList<Property> getProperties(){
        return properties;
    }
    // [[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[

    // all ownables
    public ArrayList<Ownable> getOwnables(){
        ArrayList<Ownable> res = new ArrayList<Ownable>();
        Collections.sort(properties, new Comparator<Property>() {
            @Override
            public int compare(final Property object1, final Property object2) {
                return object1.getColor().getRGB() - object2.getColor().getRGB();
            }
        });
        res.addAll(properties);
        res.addAll(hyperloops);
        res.addAll(utilities);
        return res;
    }

    // kills player
    public void lose(){
        for (Ownable o: getOwnables()){
            o.clearOwner();
            if (o instanceof Property){
                ((Property)o).removeAvocados();
            }
        }
    }

    // allows programs to access variables of player
    public String getName() {
        return name;
    }

    public Color getColour() {
        return colour;
    }

    public double getMIL(){
        return MIL;
    }

    public double getBTC(){
        return BTC;
    }

    public double getETH(){
        return ETH;
    }

    public int getLocation() {
        return location;
    }

    public static int roll(){
        Random rand = new Random();
        return (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);
    }

}
