package lecture5.jpa.entities;
import
import javax.persistence.Entity;
import javax.persistence.Basic;

@Entity
public abstract class Book extends Publication {

    @Basic
    private String author;

    // Default constructor (required for JPA)
    public Book() {
        super();
    }

    // Constructor with parameters (title, author, price, copies, ISBN10)
    public Book(String title, String author, double price, int copies, String ISBN10) {
        super(title, price, copies, ISBN10); // Calls the Publication constructor
        this.author = author;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Override the edit method from Editable
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

    // Override the initialize method from Editable
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

    // Override toString method
    @Override
    public String toString() {
        return super.toString() + ", Author: " + author;
    }
}
