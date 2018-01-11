import java.util.Random;

public class Player{

    private String name;

    private Property[] properties;
    private int location;
    private double MIL, BTC, ETH;

    Player(String playerName) {
        name = playerName;
        MIL = 1500; // Starting money
    }


    private void move(int roll) {
        // MOVE!
    }


    public String getName() {
        return name;
    }

    private double getCurrencyTotal(){
        double total = 0;
        total += MIL;
        total += ETH * Game.Ethereum.getFactorRelToMIL();
        total += BTC * Game.Bitcoin.getFactorRelToMIL();
        return round(total);
    }

    // To spend a currency, return False if not possible
    private boolean spendCurrency(String currency, double amountMIL){
        if (currency.equals("ETH")){
            // ETH is a good crypocurrency and has low transaction fees
            if (MIL < Game.Ethereum.getTransactionFee()){
                return false;
            }
            if (Game.Ethereum.convertFromMIL(amountMIL) > ETH){
                return false;
            }
            MIL -= Game.Ethereum.getTransactionFee();
            ETH -= Game.Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            // BTC is screwed and transaction fees are ridonkulous
            if (MIL < Game.Bitcoin.getTransactionFee()){
                return false;
            }
            if (Game.Bitcoin.convertToMIL(amountMIL) > BTC){
                return false;
            }
            MIL -= Game.Bitcoin.getTransactionFee();
            BTC -= Game.Bitcoin.convertToMIL(amountMIL);
        } else if (currency.equals("MIL")){
            if (MIL < amountMIL){
                return false;
            }
            MIL -= amountMIL;
        }

        return true;
    }

    private void earnCurrency(String currency, double amountMIL){
        if (currency.equals("ETH")){
            ETH += Game.Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            BTC += Game.Bitcoin.convertFromMIL(amountMIL);
        } else if (currency.equals("MIL")){
            MIL += amountMIL;
        }
    }

    // To convert a currency, with the amount being the amount spent
    private boolean convertCurrency(String fromCurr, String toCurr, double amount){
        // transaction fee will be charged here
        if (!spendCurrency(fromCurr, amount)){
            return false;
        }

        if (fromCurr.equals("ETH")){
            if (toCurr.equals("BTC")){
                earnCurrency(toCurr,
                Game.Bitcoin.convertFromMIL(Game.Ethereum.convertToMIL(amount)));
            }
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, Game.Ethereum.convertToMIL(amount));
            }
        } else if (fromCurr.equals("BTC")){
            if (toCurr.equals("ETH")){
                earnCurrency(toCurr, Game.Ethereum.convertFromMIL(Game.Bitcoin.convertToMIL(amount)));
            }
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, Game.Bitcoin.convertToMIL(amount));
            }
        } else if (fromCurr.equals("MIL")){
            if (toCurr.equals("ETH")){
                earnCurrency(toCurr, Game.Ethereum.convertFromMIL(amount));
            }
            if (toCurr.equals("BTC")){
                earnCurrency(toCurr, Game.Bitcoin.convertFromMIL(amount));
            }
        }
        return true;
    }

    public double getMIL(){
        return round(MIL);
    }

    public double getBTC(){
        return round(BTC);
    }

    public double getETH(){
        return round(ETH);
    }

    private static double round(double val){
        return Math.round(val * 100.0) / 100.0;
    }
}
