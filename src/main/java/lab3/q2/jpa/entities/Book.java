package lab3.q2.jpa.entities;

import lab3.q2.jpa.SaleableItem;

import javax.persistence.*;

@Entity(name="Book")
@DiscriminatorValue("Book")
public class Book extends Publication {

    @Basic
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void sellItem(SaleableItem si) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                "} " + super.toString();
    }

    @Override
    public void initialize() {
        this.setAuthor(getInput("Enter author: "));
    }

    @Override
    public void edit() {
        this.setAuthor(getInput("Enter author: "));
    }

    @Override
    public void sellCopy() {

    }
}


