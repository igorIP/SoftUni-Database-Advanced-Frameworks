package entities.label.basic;

import entities.contracts.Label;
import entities.shampoo.basic.BasicShampoo;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;

/*
 * @Discriminator(disabled=true)
 */
@Entity
@Table(name = "labels")
public abstract class BasicLabel implements Label {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String title;

    @Basic
    private String subtitle;

    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class,
            cascade = CascadeType.ALL)
    private BasicShampoo shampoo;


    public BasicLabel() {
    }

    public BasicLabel(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public BasicShampoo getShampoos() {
        return shampoo;
    }

    public void setShampoos(BasicShampoo shampoo) {
        this.shampoo = shampoo;
    }
}
