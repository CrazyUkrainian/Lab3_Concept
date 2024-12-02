package lab3.q2.jpa.entities;


import lab3.q2.jpa.*;
import javax.persistence.*;
import java.util.Objects;
import java.util.Scanner;

@Entity
@Table(name="pencil")
public class Pencil extends Editable implements SaleableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates the primary key for each Pencil
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private double price;

    @Column(name = "copies")
    private int copies;

    // No-argument constructor
    public Pencil() {
        this.brand = "";
        this.price = 0.0;
        this.copies = 0;
    }

    // Constructor with parameters
    public Pencil(String brand, double price, int copies) {
        this.brand = brand;
        this.price = price;
        this.copies = copies;
    }

    // Getters and Setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    @Override
    public void initialize() {
        System.out.println("Initializing a new Pencil:");
        this.brand = getInput("Enter Brand: ");
        this.price = Double.parseDouble(getInput("Enter Price: "));
        this.copies = Integer.parseInt(getInput("Enter Copies: "));
        System.out.println("Pencil initialized successfully: " + this);
    }

    // Method to edit Pencil attributes in the database
    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Method to sell a copy of the pencil
    @Override
    public void sellCopy() {
        if (copies > 0) {
            copies--;
            System.out.println("Selling Pencil: " + brand + " for $" + price);
        } else {
            System.out.println("No copies left to sell.");
        }
    }

    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pencil)) return false;
        Pencil pencil = (Pencil) obj;
        return Double.compare(pencil.price, price) == 0 &&
                copies == pencil.copies &&
                brand.equals(pencil.brand);
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(brand, price, copies);
    }

    // Override toString method
    @Override
    public String toString() {
        return "Pencil{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }

    // Helper method for input (can be replaced with actual input handling logic)
    public String getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

