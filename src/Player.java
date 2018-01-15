/**
 * [Player.java]
 * Player class
 * @author Nicholas Carr, Carol Chen
 */


import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;

public class Player{

    private String name;

    private ArrayList<Property> properties;
    private ArrayList<HyperloopTile> hyperloops;
    private ArrayList<UtilityTile> utilities;
    private int location;
    private double MIL, BTC, ETH;
    private Color colour;

    Player(String playerName, Color colour) {
        name = playerName;
        this.colour = colour;
        location = 0; // start at go
        properties = new ArrayList<Property>();
        hyperloops = new ArrayList<HyperloopTile>();
        utilities = new ArrayList<UtilityTile>();
        MIL = 1500; // Starting money
    }


    public int move(int roll) {
        location += roll;
        if (location >= 40){
            location = location % 40;
            passGo();
        }
        return location;
    }

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
            if (Game.Bitcoin.convertToMIL(amountMIL) > BTC){
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

    public void earnCurrency(String currency, double amountMIL){
        if (currency.equals("ETH")){
            ETH += Game.Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            BTC += Game.Bitcoin.convertFromMIL(amountMIL);
        } else if (currency.equals("MIL")){
            MIL += amountMIL;
        }
    }

    // To convert a currency, with the amount being the amount spent
    public boolean convertCurrency(String fromCurr, String toCurr, double amount){

        if (!spendCurrency(fromCurr, amount)){
            System.out.println("rip?");
            return false;
        }

        if (fromCurr.equals("ETH")){
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, amount);
            }
        } else if (fromCurr.equals("BTC")){
            if (toCurr.equals("MIL")){
                System.out.println("ok wtf" + amount);
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

    public void addProperty(Property p){
        properties.add(p);
    }

    public void addHyperloop(HyperloopTile h){
        hyperloops.add(h);
    }

    public void addUtility(UtilityTile u){
        utilities.add(u);
    }

    public int getHyperloops(){
        return hyperloops.size();
    }

    public int getUtilities(){
        return utilities.size();
    }

    public void lose(){
        // die user die
    }

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
