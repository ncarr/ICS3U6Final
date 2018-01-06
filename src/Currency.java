import java.util.Random;

public class Currency{

    private double factorRelToMIL;
    private int maxVariationFactor;
    private int transactionFee;

    private Random rand = new Random();

    Currency(int volatility, int fee) {
        maxVariationFactor = volatility;
        transactionFee = fee;

        // Init value
        factorRelToMIL = rand.nextDouble() * (100);
        if (rand.nextInt(2) == 0){ // 1/2 chance of it being lower than MIL
            factorRelToMIL *= -1;
        }


    }

    public void update(){

        // Setting different levels of variation factor

        double variationFactor = rand.nextDouble() * (maxVariationFactor);

        if (rand.nextInt(4) == 0){ // 1/4 chance of the crypto going down
            variationFactor *= -1;
        }
        factorRelToMIL *= variationFactor;
    }

    public double getFactorRelToMIL(){
        return factorRelToMIL;
    }

    public double convertToMIL(double val){
        return val * factorRelToMIL;
    }

    public double convertFromMIL(double val){
        return val / factorRelToMIL;
    }

    public double getValInMIL(){
        return factorRelToMIL;
    }

    public int getTransactionFee(){
        return transactionFee;
    }
}
