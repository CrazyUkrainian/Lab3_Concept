package lecture5.jpa.entities;

import lecture5.jpa.SaleableItem;
import javax.persistence.*;
import java.io.Serializable;

import lecture5.jpa.controllers.PublicationJpaController;
import lecture5.jpa.controllers.exceptions.NonexistentEntityException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // JOINED inheritance strategy for subclasses
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING) // Discriminator column
@DiscriminatorValue("Publication") // Default discriminator value for this abstract class
public abstract class Publication extends Editable implements SaleableItem, Serializable {

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
    }

    // Constructor with parameters
    public Publication(String title, double price, int copies) {
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.ISBN10 = ISBN10;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) {
        this.ISBN10 = ISBN10;
    }

    // Save the publication to the database
    public void saveToDatabase(PublicationJpaController publicationController) {
        try {
            publicationController.create(this);
            System.out.println("Publication saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving publication: " + e.getMessage());
        }
    }

    // Update the publication in the database
    public void updateInDatabase(PublicationJpaController publicationController) {
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
    public void deleteFromDatabase(PublicationJpaController publicationController) {
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
            System.out.println("Sold one copy. Remaining copies: " + copies);
            return 1; // Successfully sold
        } else {
            System.out.println("Out of stock.");
            return 0; // Failed to sell
        }
    }

    // Equals method for comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Publication that = (Publication) obj;
        return Double.compare(that.price, price) == 0 &&
                copies == that.copies &&
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
        result = 31 * result + copies;
        return result;
    }

    // toString method for printing the details
    @Override
    public String toString() {
        return String.format("Title: %s, Price: %.2f, ISBN10: %s, Copies: %d", title, price, ISBN10, copies);
    }
}
