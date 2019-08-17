package entities.ingredients;

import entities.ingredients.basic.BasicChemicalIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("AH")
public class AmoniumChloride extends BasicChemicalIngredient {

    private static final String NAME = "Ammonium Chloride";
    private static final BigDecimal PRICE = new BigDecimal(0.59);
    private static final String CHEMICAL_FORMULA = "NH4Cl";


    public AmoniumChloride() {
        super(NAME, PRICE, CHEMICAL_FORMULA);
    }
}
