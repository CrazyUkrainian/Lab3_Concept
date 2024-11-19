package lecture5.jpa.entities;

import lecture5.jpa.controllers.BookJpaController;
import lecture5.jpa.controllers.exceptions.NonexistentEntityException;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Entity
public abstract class Book extends Publication {

    private static final String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static BookJpaController bookController = new BookJpaController(emf);

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

    // Save the book using BookJpaController
    public void saveToDatabase() {
        try {
            bookController.create(this);
            System.out.println("Book saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving book: " + e.getMessage());
        }
    }

    // Update the book using BookJpaController
    public void updateInDatabase() {
        try {
            bookController.edit(this);
            System.out.println("Book updated successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The book no longer exists.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    // Delete the book using BookJpaController
    public void deleteFromDatabase() {
        try {
            bookController.destroy(getId());
            System.out.println("Book deleted successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The book no longer exists.");
        }
    }

    // Override edit() to update book details
    @Override
    public void edit() {
        System.out.println("Editing book details:");
        setTitle(getInputString("Enter new title: "));
        setPrice(getInputDouble("Enter new price: "));
        setCopies(getInputInt("Enter new number of copies: "));
        setAuthor(getInputString("Enter new author: "));
        setISBN10(getInputString("Enter new ISBN10: "));

        // Update in database
        updateInDatabase();
    }

    // Override initialize() to initialize a new book
    @Override
    public void initialize() {
        System.out.println("Initializing a new book:");
        setTitle(getInputString("Enter title: "));
        setPrice(getInputDouble("Enter price: "));
        setCopies(getInputInt("Enter number of copies: "));
        setAuthor(getInputString("Enter author: "));
        setISBN10(getInputString("Enter ISBN10: "));

        // Save to database
        saveToDatabase();
    }

    // Override toString() to include the author and ISBN
    @Override
    public String toString() {
        return super.toString() + ", Author: " + author + ", ISBN10: " + ISBN10;
    }
}
