package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;


    @ManyToMany(targetEntity = Continent.class)
    @JoinTable(name = "countries_continents",
            joinColumns = {@JoinColumn(name = "country_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "continent_id", referencedColumnName = "id")})
    private Set<Continent> continent;


    public Country() {
    }
}
