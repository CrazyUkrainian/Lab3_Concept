package lecture5.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Column;
import java.sql.Date;

import lecture5.jpa.controllers.MagazineJpaController;
import lecture5.jpa.controllers.exceptions.NonexistentEntityException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Entity
public abstract class Magazine extends Publication {

    private static final String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static MagazineJpaController magazineController = new MagazineJpaController(emf);

    @Basic
    private int orderQty;

    @Basic
    private Date currIssue;

    @Column(name = "isbn13")
    private String isbn13;

    // Default constructor required for JPA
    public Magazine(String title, double price, int quantity) {
        super();
    }

    // Constructor with parameters
    public Magazine(String title, double price, int copies, int orderQty) {
        super(title, price, copies); // Call to the Publication constructor
        this.orderQty = orderQty;
        this.currIssue = new Date(System.currentTimeMillis()); // Initialize current issue to current date
    }

    // Getter and Setter for orderQty
    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    // Getter and Setter for currIssue
    public Date getCurrIssue() {
        return currIssue;
    }

    public void setCurrIssue(Date currIssue) {
        this.currIssue = currIssue;
    }

    // Getter and Setter for isbn13
    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    // Save the Magazine to the database
    public void saveToDatabase() {
        try {
            magazineController.create(this);
            System.out.println("Magazine saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving Magazine: " + e.getMessage());
        }
    }

    // Update the Magazine in the database
    public void updateInDatabase() {
        try {
            magazineController.edit(this);
            System.out.println("Magazine updated successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The Magazine no longer exists.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating Magazine: " + e.getMessage());
        }
    }

    // Delete the Magazine from the database
    public void deleteFromDatabase() {
        try {
            magazineController.destroy(getId());
            System.out.println("Magazine deleted successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The Magazine no longer exists.");
        }
    }

    // Override edit() to update Magazine details
    @Override
    public void edit() {
        System.out.println("Editing Magazine details:");
        setTitle(getInputString("Enter new title: "));
        setPrice(getInputDouble("Enter new price: "));
        setCopies(getInputInt("Enter new number of copies: "));
        setOrderQty(getInputInt("Enter new order quantity: "));
        setCurrIssue((Date) getInputDate("Enter new current issue date (yyyy-mm-dd): "));
        setIsbn13(getInputString("Enter new ISBN13: "));

        // Update in database
        updateInDatabase();
    }

    // Override initialize() to initialize a new Magazine
    @Override
    public void initialize() {
        System.out.println("Initializing a new Magazine:");
        setTitle(getInputString("Enter title: "));
        setPrice(getInputDouble("Enter price: "));
        setCopies(getInputInt("Enter number of copies: "));
        setOrderQty(getInputInt("Enter order quantity: "));
        setCurrIssue((Date) getInputDate("Enter current issue date (yyyy-mm-dd): "));
        setIsbn13(getInputString("Enter ISBN13: "));

        // Save to database
        saveToDatabase();
    }

    // Override toString() method
    @Override
    public String toString() {
        return super.toString() + ", Order Qty: " + orderQty + ", Current Issue: " + currIssue + ", ISBN13: " + isbn13;
    }
}
