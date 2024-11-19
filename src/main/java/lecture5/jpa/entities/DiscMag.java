package lecture5.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;

import lecture5.jpa.controllers.DiscMagJpaController;
import lecture5.jpa.controllers.exceptions.NonexistentEntityException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Entity
public abstract class DiscMag extends Magazine {

    private static final String PERSISTENCE_UNIT_NAME = "DEFAULT_PU";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static DiscMagJpaController discMagController = new DiscMagJpaController(emf);

    @Basic
    private boolean hasDisc;


    // Constructor with parameters
    public DiscMag(String title, double price, int copies, int orderQty, boolean hasDisc) {
        super(title, price, copies, orderQty);
        this.hasDisc = hasDisc;
    }

    // Getter for hasDisc
    public boolean isHasDisc() {
        return hasDisc;
    }

    // Setter for hasDisc
    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    // Save the DiscMag to the database
    public void saveToDatabase() {
        try {
            discMagController.create(this);
            System.out.println("Disc Magazine saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving Disc Magazine: " + e.getMessage());
        }
    }

    // Update the DiscMag in the database
    public void updateInDatabase() {
        try {
            discMagController.edit(this);
            System.out.println("Disc Magazine updated successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The Disc Magazine no longer exists.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating Disc Magazine: " + e.getMessage());
        }
    }

    // Delete the DiscMag from the database
    public void deleteFromDatabase() {
        try {
            discMagController.destroy(getId());
            System.out.println("Disc Magazine deleted successfully.");
        } catch (NonexistentEntityException e) {
            System.out.println("Error: The Disc Magazine no longer exists.");
        }
    }

    // Override edit() to update DiscMag details
    @Override
    public void edit() {
        System.out.println("Editing Disc Magazine details:");
        setTitle(getInputString("Enter new title: "));
        setPrice(getInputDouble("Enter new price: "));
        setCopies(getInputInt("Enter new number of copies: "));
        setOrderQty(getInputInt("Enter new order quantity: "));
        setHasDisc(getInputBoolean("Does it have a disc? (true/false): "));

        // Update in database
        updateInDatabase();
    }

    // Override initialize() to initialize a new DiscMag
    @Override
    public void initialize() {
        System.out.println("Initializing a new Disc Magazine:");
        setTitle(getInputString("Enter title: "));
        setPrice(getInputDouble("Enter price: "));
        setCopies(getInputInt("Enter number of copies: "));
        setOrderQty(getInputInt("Enter order quantity: "));
        setHasDisc(getInputBoolean("Does it have a disc? (true/false): "));

        // Save to database
        saveToDatabase();
    }

    // Override toString() to include hasDisc information
    @Override
    public String toString() {
        return super.toString() + ", Has Disc: " + (hasDisc ? "Yes" : "No");
    }
}
