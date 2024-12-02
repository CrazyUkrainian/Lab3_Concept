package lab3.q2.jpa;

public class CashTill {

    private static CashTill instance;
    private double runningTotal;


    private CashTill() {
        runningTotal = 0.0;
    }
    public static CashTill getInstance() {
        if (instance == null) {
            instance = new CashTill();
        }
        return instance;
    }
    public void addToTotal(double amount) {
        runningTotal += amount;
    }
    public double getTotal() {
        return runningTotal;
    }
    public void resetTotal() {
        runningTotal = 0;
    }
}