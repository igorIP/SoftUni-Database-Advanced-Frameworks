package entities.contracts;

import entities.Size;
import entities.ingredients.basic.BasicIngredient;
import entities.label.basic.BasicLabel;

import java.math.BigDecimal;
import java.util.Set;

public interface Shampoo {

    long getId();

    void setId(long id);

    String getBrand();

    void setBrand(String brand);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Size getSize();

    void setSize(Size size);

    BasicLabel getBasicLabel();

    void setBasicLabel(BasicLabel basicLabel);

    Set<BasicIngredient> getIngredients();

    void setIngridients(Set<BasicIngredient> ingredients);
}
