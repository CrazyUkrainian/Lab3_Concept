package lecture5.jpa.entities;

import lecture5.jpa.SaleableItem;
import javax.persistence.*;
import java.io.Serializable;

import lecture5.jpa.controllers.PublicationJpaController;
import lecture5.jpa.controllers.exceptions.NonexistentEntityException;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // Use JOINED inheritance strategy for subclasses
public abstract class Publication extends Editable implements SaleableItem, Serializable {

    private static final String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static PublicationJpaController publicationController = new PublicationJpaController(emf);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String title;

    @Basic
    private double price;

    @Basic
    private int copies;

    @Basic
    private String ISBN10;

    // Default constructor required for JPA
    public Publication() {
        // No-argument constructor
    }

    // Constructor with parameters
    public Publication(String title, double price, int copies) {
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.ISBN10 = ISBN10;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter and Setter for copies
    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    // Getter and Setter for ISBN10
    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) {
        this.ISBN10 = ISBN10;
    }

    // Save the publication to the database
    public void saveToDatabase() {
        try {
            publicationController.create(this);
            System.out.println("Publication saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving publication: " + e.getMessage());
        }
    }

    // Update the publication in the database
    public void updateInDatabase() {
        try {
            publicationController.edit(this);
            System.out.println("Publication updated successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The publication no longer exists.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating publication: " + e.getMessage());
        }
    }

    // Delete the publication from the database
    public void deleteFromDatabase() {
        try {
            publicationController.destroy(getId());
            System.out.println("Publication deleted successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The publication no longer exists.");
        }
    }

    // Method to sell a copy
    public short sellCopy() {
        if (copies > 0) {
            copies--;
        } else {
            System.out.println("Out of stock.");
        }
        return 0;
    }

    // Equals method for comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Publication that = (Publication) obj;
        return Double.compare(that.price, price) == 0 &&
                title.equals(that.title) &&
                ISBN10.equals(that.ISBN10);
    }

    // HashCode method
    @Override
    public int hashCode() {
        int result = title.hashCode();
        long temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + ISBN10.hashCode();
        return result;
    }

    // toString method for printing the details
    @Override
    public String toString() {
        return String.format("Title: %s, Price: %.2f, ISBN10: %s, Copies: %d", title, price, ISBN10, copies);
    }
}
