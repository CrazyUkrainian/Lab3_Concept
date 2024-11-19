package lecture5.jpa.entities;

import lecture5.jpa.SaleableItem;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lecture5.jpa.controllers.TicketJpaController;
import lecture5.jpa.controllers.exceptions.NonexistentEntityException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Entity
public abstract class Ticket extends Editable implements SaleableItem {

    private static final String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static TicketJpaController ticketController = new TicketJpaController(emf);

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

    // Save the Ticket to the database
    public void saveToDatabase() {
        try {
            ticketController.create(this);
            System.out.println("Ticket saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving Ticket: " + e.getMessage());
        }
    }

    // Update the Ticket in the database
    public void updateInDatabase() {
        try {
            ticketController.edit(this);
            System.out.println("Ticket updated successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The Ticket no longer exists.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating Ticket: " + e.getMessage());
        }
    }

    // Delete the Ticket from the database
    public void deleteFromDatabase() {
        try {
            ticketController.destroy(getId());
            System.out.println("Ticket deleted successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The Ticket no longer exists.");
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

        // Update in database
        updateInDatabase();
    }

    @Override
    public void initialize() {
        // Logic to initialize a new ticket
        System.out.println("Initializing a new ticket:");
        setDescription(getInputString("Enter description: "));
        setTicketPrice(getInputDouble("Enter price: "));
        setQuantity(getInputInt("Enter quantity: "));

        // Save to database
        saveToDatabase();
    }

    @Override
    public String toString() {
        return "Ticket ID: " + id + ", Description: " + description + ", Price: " + price + ", Quantity: " + quantity;
    }
}
