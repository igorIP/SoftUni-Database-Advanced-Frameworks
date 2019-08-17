package usersystemapp.domain.entities.models;

import usersystemapp.annotations.email.Email;
import usersystemapp.annotations.password.Password;
import usersystemapp.domain.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
            name = "name")
    @Size(min = 4, max = 30)
    private String name;


    //@JoinColumn(name = "country_id", referencedColumnName = "id") -> option 1
    @NotNull//single-parameter constraint
    @ManyToOne(targetEntity = Country.class)
    @JoinTable(name = "towns_countries", // option --> 2
    joinColumns = {@JoinColumn(name = "town_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "country_id", referencedColumnName = "id")})
    private Country country;

    public Town() {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }
}
