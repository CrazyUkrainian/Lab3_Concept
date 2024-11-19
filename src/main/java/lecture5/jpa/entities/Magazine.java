package lecture5.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Column;
import java.sql.Date;

@Entity
public abstract class Magazine extends Publication {

    @Basic
    private int orderQty;

    @Basic
    private Date currIssue;

    @Column(name = "isbn13")
    private String isbn13;

    // Default constructor required for JPA
    public Magazine(String title, double price, int quantity, String isbn13) {
        super();
    }

    // Constructor with parameters
    public Magazine(String title, double price, int copies, int orderQty) {
        super(title, price, copies); // Call to the Publication constructor
        this.orderQty = orderQty;
        this.currIssue = new Date(System.currentTimeMillis()); // Initialize current issue to current date
        this.isbn13 = isbn13; // Set ISBN13
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

    // Override toString() method
    @Override
    public String toString() {
        return super.toString() + ", Order Qty: " + orderQty + ", Current Issue: " + currIssue + ", ISBN13: " + isbn13;
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
    }
}
