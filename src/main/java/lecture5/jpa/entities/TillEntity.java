package lecture5.jpa.entities;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Single table for TillEntity and subclasses
@DiscriminatorColumn(name = "TillType", discriminatorType = DiscriminatorType.STRING) // Discriminator column
public abstract class TillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getter and setter for the id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

