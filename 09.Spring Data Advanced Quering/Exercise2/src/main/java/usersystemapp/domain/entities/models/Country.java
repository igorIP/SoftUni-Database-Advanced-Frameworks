package usersystemapp.domain.entities.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
            name = "name")
    @Size(min = 1, max = 50)
    private String name;


    @NotNull//single-parameter constraints
    @OneToMany(targetEntity = Town.class)
    @JoinTable(name = "towns_countries",
            joinColumns = {@JoinColumn(name = "country_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "town_id", referencedColumnName = "id")})
    private Set<Town> towns;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(final Set<Town> towns) {
        this.towns = towns;
    }
}
