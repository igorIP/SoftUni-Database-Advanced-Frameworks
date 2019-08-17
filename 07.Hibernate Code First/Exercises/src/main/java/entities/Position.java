package entities;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @Column(columnDefinition = "VARCHAR(2)")
    private int id;

    @Column(name = "position_description", columnDefinition = "VARCHAR(50)")
    private String positionDescription;


    public Position() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
}
