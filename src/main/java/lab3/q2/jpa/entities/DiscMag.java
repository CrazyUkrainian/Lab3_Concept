package lab3.q2.jpa.entities;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;

import javax.persistence.InheritanceType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "disc_magazines")
@DiscriminatorValue("DiscMag")  // Specify the subclass name for single-table inheritance
@Inheritance(strategy = InheritanceType.JOINED)  // Use joined inheritance strategy
public class DiscMag extends Magazine {

    @Column(name = "has_disc")
    private boolean hasDisc;

    // No-argument constructor
    public DiscMag() {
        super(); // Calls Magazine's no-argument constructor
        this.hasDisc = false; // Default value
    }

    // Constructor with hasDisc
    public DiscMag(boolean hasDisc) {
        super(); // Calls Magazine's no-argument constructor
        this.hasDisc = hasDisc;
    }

    // Constructor with all parameters
    public DiscMag(boolean hasDisc, int orderQty, Date currIssue) {
        super("", 0.0, 0, orderQty, currIssue); // Default values for title and price
        this.hasDisc = hasDisc;
    }

    // Constructor with title, price, copies, and hasDisc
    public DiscMag(String title, double price, int copies, boolean hasDisc, int orderQty, Date currIssue) {
        super(title, price, copies, orderQty, currIssue);
        this.hasDisc = hasDisc;
    }

    public boolean isHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    @Override
    public void edit() {
        super.edit(); // Call the parent edit method
        String hasDiscInput = getInput("Has Disc (true/false, leave blank to keep current): ");
        if (!hasDiscInput.isEmpty()) {
            this.hasDisc = Boolean.parseBoolean(hasDiscInput);
        }
        System.out.println("DiscMag updated successfully.");
    }

    @Override
    public void initialize() {
        super.initialize(); // Call the parent initialize method
        String hasDiscInput = getInput("Has Disc (true/false): ");
        this.hasDisc = Boolean.parseBoolean(hasDiscInput);
        System.out.println("DiscMag initialized successfully: " + this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DiscMag)) return false;
        if (!super.equals(obj)) return false;
        DiscMag discMag = (DiscMag) obj;
        return hasDisc == discMag.hasDisc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasDisc);
    }

    @Override
    public String toString() {
        return "DiscMag{" +
                "title='" + this.getTitle() + '\'' +
                ", price=" + this.getPrice() +
                ", orderQty=" + this.getOrderQty() +
                ", currIssue=" + this.getCurrIssue() +
                ", hasDisc=" + hasDisc +
                '}';
    }
}
