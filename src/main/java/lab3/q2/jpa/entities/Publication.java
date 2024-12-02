package lab3.q2.jpa.entities;

import lab3.q2.jpa.Editable;
import lab3.q2.jpa.SaleableItem;
import javax.persistence.*;
import java.io.Serializable;

import lab3.q2.jpa.Editable;
import lab3.q2.jpa.SaleableItem;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Publication extends Editable implements SaleableItem, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    @Basic
    @Column(name="title", nullable = false)
    private String title;
    @Basic
    @Column(name="copies", nullable = false)
    private int copies;


    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    @Basic
    @Column(name="price", nullable = false)
    private double price;

    public Long getId() {
        return id;
    }

    public abstract void sellItem(SaleableItem si);

    public abstract void initialize();

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}