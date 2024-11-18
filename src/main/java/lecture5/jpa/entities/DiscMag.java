package lecture5.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class DiscMag extends Magazine {

    @Basic
    private boolean hasDisc;

    // Default constructor for JPA
    public DiscMag() {
        super();
    }

    // Constructor with parameters for title, price, copies, orderQty, and hasDisc
    public DiscMag(String title, double price, int copies, int orderQty, boolean hasDisc) {
        super(title, price, copies, orderQty); // Calls the parent constructor
        this.hasDisc = hasDisc;
    }

    // Getter for hasDisc
    public boolean isHasDisc() {
        return hasDisc;
    }

    // Setter for hasDisc
    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    // Override edit() to update DiscMag details
    @Override
    public void edit() {
        // Logic to edit a DiscMag's details
        System.out.println("Editing Disc Magazine details:");
        setTitle(getInputString("Enter new title: "));
        setPrice(getInputDouble("Enter new price: "));
        setCopies(getInputInt("Enter new number of copies: "));
        setOrderQty(getInputInt("Enter new order quantity: "));
        setHasDisc(getInputBoolean("Does it have a disc? (true/false): "));
    }

    // Override initialize() to initialize a new DiscMag
    @Override
    public void initialize() {
        // Logic to initialize a new DiscMag
        System.out.println("Initializing a new Disc Magazine:");
        setTitle(getInputString("Enter title: "));
        setPrice(getInputDouble("Enter price: "));
        setCopies(getInputInt("Enter number of copies: "));
        setOrderQty(getInputInt("Enter order quantity: "));
        setHasDisc(getInputBoolean("Does it have a disc? (true/false): "));
    }

    // Override toString() to include hasDisc information
    @Override
    public String toString() {
        return super.toString() + ", Has Disc: " + (hasDisc ? "Yes" : "No");
    }
}
