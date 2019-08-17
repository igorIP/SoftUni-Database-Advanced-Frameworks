package usersystemapp.domain.entities.models;

import usersystemapp.domain.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Table(name = "countries")
public class Country {

    private Long id;
    private String name;
    private Set<Town> towns;

    public Country() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(nullable = false,
            name = "name")
    @Size(min = 1, max = 50)
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    //@NotNull//single-parameter constraints
    //@OneToMany(targetEntity = Town.class)
    //@JoinTable(name = "countries_towns",
    //joinColumns = {@JoinColumn(name = "country_id", referencedColumnName = "id")},
    //inverseJoinColumns = {@JoinColumn(name = "town_id", referencedColumnName = "id")})
    @NotNull
    @OneToMany(mappedBy = "country")
    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(final Set<Town> towns) {
        this.towns = towns;
    }
}
