package entities.shampoo.basic;

import entities.Size;
import entities.contracts.Shampoo;
import entities.ingredients.basic.BasicIngredient;
import entities.label.basic.BasicLabel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo implements Shampoo {

    @Id
    private long id;

    @Basic
    private String brand;

    @Basic
    private BigDecimal price;

    @Enumerated
    private Size size;

    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    private BasicLabel label;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id",
                    referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    protected BasicShampoo() {
        this.ingredients = new HashSet<>();
    }

    protected BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel label) {
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.label = label;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }

    @Override
    public String getBrand() {
        return null;
    }

    @Override
    public void setBrand(String brand) {

    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public void setPrice(BigDecimal price) {

    }

    @Override
    public Size getSize() {
        return null;
    }

    @Override
    public void setSize(Size size) {

    }

    @Override
    public BasicLabel getBasicLabel() {
        return null;
    }

    @Override
    public void setBasicLabel(BasicLabel basicLabel) {
        this.label = basicLabel;
    }

    @Override
    public Set<BasicIngredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public void setIngridients(Set<BasicIngredient> ingredients) {

    }
}
