package lecture5.jpa.entities;

import lecture5.jpa.controllers.CashTillJpaController;
import javax.persistence.Entity;

import java.text.DecimalFormat;
import javax.persistence.*;


@Entity
@DiscriminatorValue("CashTill") // Discriminator value for CashTill
public class CashTill extends TillEntity {

    private double runningTotal;

    // Default constructor required for JPA
    public CashTill() {
        this.runningTotal = 0;
    }

    private static CashTill instance;

    // Singleton instance method
    public static CashTill getInstance() {
        if (instance == null) {
            synchronized (CashTill.class) { // Thread-safe lazy initialization
                if (instance == null) {
                    instance = new CashTill();
                }
            }
        }
        return instance;
    }

    // Method to add to the total
    public void addToTotal(double amount) {
        if (amount > 0) { // Ensure only positive amounts are added
            runningTotal += amount;
        } else {
            System.out.println("Cannot add a negative amount.");
        }
    }

    // Method to get the current total (getter)
    public double getTotal() {
        return runningTotal;
    }

    // Method to display the total formatted as currency
    public void showTotal() {
        DecimalFormat df = new DecimalFormat("$#.00"); // Format to two decimal places
        System.out.println("Total Cash in Till: " + df.format(runningTotal));
    }

    // Add persistence methods (optional for convenience)
    public void saveToDatabase(CashTillJpaController controller) {
        if (getId() == null) {
            controller.create(this);
        } else {
            try {
                controller.edit(this);
            } catch (Exception e) {
                System.out.println("Error saving CashTill: " + e.getMessage());
            }
        }
    }
}