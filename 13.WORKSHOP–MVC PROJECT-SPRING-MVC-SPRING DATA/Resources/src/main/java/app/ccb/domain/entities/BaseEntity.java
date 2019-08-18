package app.ccb.domain.entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    private Integer id;

    protected BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
