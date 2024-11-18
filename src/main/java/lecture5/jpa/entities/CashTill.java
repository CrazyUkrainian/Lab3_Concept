package lecture5.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.text.DecimalFormat;

@Entity
public class CashTill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate an ID for the entity
    private Long id;  // Primary key field for the entity

    private double runningTotal;

    // Default constructor required for JPA
    public CashTill() {
        this.runningTotal = 0;
    }

    // Method to add to the total
    public void addToTotal(double amount) {
        if (amount > 0) {  // Ensure only positive amounts are added
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

    // Getter and setter for the id (required for JPA)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
