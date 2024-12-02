package lab3.q2.jpa.entities;

import lab3.q2.jpa.SaleableItem;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Magazine extends Publication {

    @Basic
    private int orderQty;
    @Basic
    private Date currIssue;
    @Id
    private Long id;

    public Magazine(String s, double v, int i, int orderQty, Date currIssue) {
        super();
        this.orderQty = orderQty;
        this.currIssue = currIssue;
    }

    public Magazine() {
        super();
        this.orderQty = 0;
        this.currIssue = new Date();
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "orderQty=" + orderQty +
                ", currIssue=" + currIssue +
                "} " + super.toString();
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public Date getCurrIssue() {
        return currIssue;
    }

    public void setCurrIssue(Date currIssue) {
        this.currIssue = currIssue;
    }

    @Override
    public void sellItem(SaleableItem si) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public void sellCopy() {
        //do nothing
    }
}