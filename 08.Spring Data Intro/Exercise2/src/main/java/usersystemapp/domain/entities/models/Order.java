package usersystemapp.domain.entities.models;

import usersystemapp.domain.entities.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order{

    private Long id;

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
