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
@DiscriminatorValue("DiscMag")
@Inheritance(strategy = InheritanceType.JOINED)
public class DiscMag extends Magazine {

    @Column(name = "has_disc")
    private boolean hasDisc;


    public DiscMag() {
        super();
        this.hasDisc = false;
    }


    public DiscMag(boolean hasDisc) {
        super();
        this.hasDisc = hasDisc;
    }


    public DiscMag(boolean hasDisc, int orderQty, Date currIssue) {
        super("", 0.0, 0, orderQty, currIssue);
        this.hasDisc = hasDisc;
    }


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
        String hasDiscInput = getInput("Is it got disk - true or false? keep blank if want): ");
        if (!hasDiscInput.isEmpty()) {
            this.hasDisc = Boolean.parseBoolean(hasDiscInput);
        }
        System.out.println("updated successfully.");
    }

    @Override
    public void initialize() {
        super.initialize();
        String hasDiscInput = getInput("False/true is it has disc? ");
        this.hasDisc = Boolean.parseBoolean(hasDiscInput);
        System.out.println("initialized successfully: " + this);
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
