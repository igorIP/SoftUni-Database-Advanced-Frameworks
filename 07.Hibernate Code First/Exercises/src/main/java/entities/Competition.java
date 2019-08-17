package entities;

import javax.persistence.*;

@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @OneToOne
    private CompetitionType type;


    public Competition() {
    }
}
