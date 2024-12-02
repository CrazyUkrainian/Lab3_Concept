package lab3.q2.jpa.entities;

import lab3.q2.jpa.Editable;
import lab3.q2.jpa.SaleableItem;

import javax.persistence.*;

@Entity  // Mark this class as a JPA entity
@Table(name = "tickets")  // Specify the table name in the database
public class Ticket extends Editable implements SaleableItem {

    @Id  // Marks the id field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the primary key
    private Long id;

    @Column(name = "event_name")  // Map eventName field to the database column
    private String eventName;

    @Column(name = "event_date")  // Map event date field to the database column
    private String date;

    @Column(name = "price")  // Map price field to the database column
    private double price;

    @Column(name = "copies")  // Map copies field to the database column
    private int copies;

    // Default constructor
    public Ticket() {
        this.eventName = "Unknown Event";
        this.date = "Unknown Date";
        this.price = 0.0;
        this.copies = 0;
    }

    // Parameterized constructor
    public Ticket(String eventName, String date, double price, int copies) {
        this.eventName = eventName;
        this.date = date;
        this.price = price;
        this.copies = copies;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    // Implement sellCopy() from SaleableItem interface
    @Override
    public void sellCopy() {
        if (copies > 0) {
            copies--; // Reduce copies when sold
            System.out.println("Ticket for " + eventName + " sold.");
        } else {
            System.out.println("No copies available for " + eventName + ".");
        }
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "eventName='" + eventName + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }
}
