public class Player{

    private static String name;
    
    private int location;
    private double MIL, BTC, ETH;

    Player(String playerName) {
        name = playerName;
        MIL = 1500; // Starting money
    }


    public void move(int roll) {
        // MOVE!
    }

    public double getCurrencyTotal(){
        double total;
        total += MIL;
        total += ETH * Ethereum.getFactorRelToMIL();
        total += BTC * Bitcoin.getFactorRelToMIL();
        return round(total);
    }

    // To spend a currency, return False if not possible
    public boolean spendCurrency(String currency, double amountMIL){
        if (currency.equals("ETH")){
            // ETH is a good crypocurrency and has low transaction fees
            if (MIL < Ethereum.getTransactionFee()){
                return False;
            }
            if (Ethereum.convertFromMIL(amountMIL) > ETH){
                return False;
            }
            MIL -= ETH.getTransactionFee;
            ETH -= Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            // BTC is screwed and transaction fees are ridonkulous
            if (MIL < Bitcoin.getTransactionFee()){
                return False;
            }
            if (Bitcoin.convertToMIL(amountMIL) > BTC){
                return False;
            }
            MIL -= Bitcoin.getTransactionFee;
            BTC -= Bitcoin.convertToMIL(amountMIL);
        } else if (currency.equals("MIL")){
            if (MIL < amountMIL){
                return False;
            }
            MIL -= amountMIL;
        }

        return true;
    }

    public void earnCurrency(String currency, double amountMIL){
        if (currency.equals("ETH")){
            ETH += Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            BTC += Bitcoin.convertFromMIL(amountMIL);
        } else if (currency.equals("MIL")){
            MIL += amountMIL;
        }
    }

    // To convert a currency, with the amount being the amount spent
    public boolean convertCurrency(String fromCurr, String toCurr, double amount){
        // transaction fee will be charged here
        if (!spendCurrency(fromCurr, amount)){
            return false;
        }

        if (fromCurr.equals("ETH")){
            if (toCurr.equals("BTC")){
                earnCurrency(toCurr, Bitcoin.convertFromMIL(Ethereum.convertToMIL(amount)));
            }
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, Ethereum.convertToMIL(amount));
            }
        } else if (fromCurr.equals("BTC")){
            if (toCurr.equals("ETH")){
                earnCurrency(toCurr, Ethereum.convertFromMIL(Bitcoin.convertToMIL(amount)));
            }
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, Bitcoin.convertToMIL(amount));
            }
        } else if (fromCurr.equals("MIL")){
            if (toCurr.equals("ETH")){
                earnCurrency(toCurr, Ethereum.convertFromMIL(amount));
            }
            if (toCurr.equals("BTC")){
                earnCurrency(toCurr, Bitcoin.convertFromMIL(amount));
            }
        }
        return true;
    }

    public double getMIL(boolean){
        return round(MIL);
    }

    public double getBTC(boolean){
        return round(BTC);
    }

    public double getETH(boolean){
        return round(ETH);
    }

    private static double round(double val){
        return Math.round(val * 100.0) / 100.0
    }
}
