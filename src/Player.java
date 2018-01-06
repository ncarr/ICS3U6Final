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
        double total = 0;
        total += MIL;
        total += ETH * Millenialopoly.Ethereum.getFactorRelToMIL();
        total += BTC * Millenialopoly.Bitcoin.getFactorRelToMIL();
        return round(total);
    }

    // To spend a currency, return False if not possible
    public boolean spendCurrency(String currency, double amountMIL){
        if (currency.equals("ETH")){
            // ETH is a good crypocurrency and has low transaction fees
            if (MIL < Millenialopoly.Ethereum.getTransactionFee()){
                return false;
            }
            if (Millenialopoly.Ethereum.convertFromMIL(amountMIL) > ETH){
                return false;
            }
            MIL -= Millenialopoly.Ethereum.getTransactionFee();
            ETH -= Millenialopoly.Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            // BTC is screwed and transaction fees are ridonkulous
            if (MIL < Millenialopoly.Bitcoin.getTransactionFee()){
                return false;
            }
            if (Millenialopoly.Bitcoin.convertToMIL(amountMIL) > BTC){
                return false;
            }
            MIL -= Millenialopoly.Bitcoin.getTransactionFee();
            BTC -= Millenialopoly.Bitcoin.convertToMIL(amountMIL);
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
            ETH += Millenialopoly.Ethereum.convertFromMIL(amountMIL);
        } else if (currency.equals("BTC")){
            BTC += Millenialopoly.Bitcoin.convertFromMIL(amountMIL);
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
                earnCurrency(toCurr,
                Millenialopoly.Bitcoin.convertFromMIL(Millenialopoly.Ethereum.convertToMIL(amount)));
            }
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, Millenialopoly.Ethereum.convertToMIL(amount));
            }
        } else if (fromCurr.equals("BTC")){
            if (toCurr.equals("ETH")){
                earnCurrency(toCurr, Millenialopoly.Ethereum.convertFromMIL(Millenialopoly.Bitcoin.convertToMIL(amount)));
            }
            if (toCurr.equals("MIL")){
                earnCurrency(toCurr, Millenialopoly.Bitcoin.convertToMIL(amount));
            }
        } else if (fromCurr.equals("MIL")){
            if (toCurr.equals("ETH")){
                earnCurrency(toCurr, Millenialopoly.Ethereum.convertFromMIL(amount));
            }
            if (toCurr.equals("BTC")){
                earnCurrency(toCurr, Millenialopoly.Bitcoin.convertFromMIL(amount));
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
