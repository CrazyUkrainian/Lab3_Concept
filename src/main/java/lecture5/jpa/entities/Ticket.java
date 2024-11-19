package lecture5.jpa.entities;

import lecture5.jpa.SaleableItem;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public abstract class Ticket extends Editable implements SaleableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key field for the entity

    @Basic
    private String description;

    @Basic
    private double price;

    @Basic
    private int quantity;

    // Default constructor required for JPA
    public Ticket() {
        super();
    }

    // Constructor with parameters
    public Ticket(String description, double price, int quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter for id (required for JPA)
    public Long getId() {
        return id;
    }

    // Getters and Setters for description, price, and quantity
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTicketPrice() {
        return price;
    }

    public void setTicketPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Implemented methods from SaleableItem
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public short sellCopy() {
        if (quantity > 0) {
            quantity--;
            System.out.println("Ticket sold: " + description);
            return 1; // Indicates successful sale
        } else {
            System.out.println("No more tickets available.");
            return 0; // Indicates no stock
        }
    }

    // Editable methods
    @Override
    public void edit() {
        // Logic to edit ticket's details
        System.out.println("Editing ticket details:");
        setDescription(getInputString("Enter new description: "));
        setTicketPrice(getInputDouble("Enter new price: "));
        setQuantity(getInputInt("Enter new quantity: "));
    }

    @Override
    public void initialize() {
        // Logic to initialize a new ticket
        System.out.println("Initializing a new ticket:");
        setDescription(getInputString("Enter description: "));
        setTicketPrice(getInputDouble("Enter price: "));
        setQuantity(getInputInt("Enter quantity: "));
    }

    @Override
    public String toString() {
        return "Ticket ID: " + id + ", Description: " + description + ", Price: " + price + ", Quantity: " + quantity;
    }
}
