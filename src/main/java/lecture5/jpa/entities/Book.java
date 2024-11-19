package lecture5.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public abstract class Book extends Publication {

    @Basic
    private String author;
    private String ISBN10;

    // Default constructor for JPA
    public Book() {
        super();
    }

    // Constructor with parameters
    public Book(String title, String author, double price, int copies, String ISBN10) {
        super(title, price, copies);
        this.author = author;
        this.ISBN10 = ISBN10;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter for ISBN10
    public String getISBN10() {
        return ISBN10;
    }

    // Setter for ISBN10
    public void setISBN10(String ISBN10) {
        this.ISBN10 = ISBN10;
    }

    // Override edit() to update book details
    @Override
    public void edit() {
        // Logic to edit a book's details
        System.out.println("Editing book details:");
        setTitle(getInputString("Enter new title: "));
        setPrice(getInputDouble("Enter new price: "));
        setCopies(getInputInt("Enter new number of copies: "));
        setAuthor(getInputString("Enter new author: "));
        setISBN10(getInputString("Enter new ISBN10: "));
    }

    // Override initialize() to initialize a new book
    @Override
    public void initialize() {
        // Logic to initialize a new book
        System.out.println("Initializing a new book:");
        setTitle(getInputString("Enter title: "));
        setPrice(getInputDouble("Enter price: "));
        setCopies(getInputInt("Enter number of copies: "));
        setAuthor(getInputString("Enter author: "));
        setISBN10(getInputString("Enter ISBN10: "));
    }

    // Override toString() to include the author and ISBN
    @Override
    public String toString() {
        return super.toString() + ", Author: " + author + ", ISBN10: " + ISBN10;
    }
}
