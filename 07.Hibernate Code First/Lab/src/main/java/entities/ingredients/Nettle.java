package entities.ingredients;

import entities.ingredients.basic.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("NT")
public class Nettle extends BasicIngredient {

    private static final String NAME = "Nettle";
    private static final BigDecimal PRICE = new BigDecimal(6.12);

    public Nettle() {
        super(NAME, PRICE);
    }
}
