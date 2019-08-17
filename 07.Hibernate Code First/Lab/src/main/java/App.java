import entities.ingredients.*;
import entities.ingredients.basic.BasicIngredient;
import entities.label.ClassicLabel1;
import entities.label.basic.BasicLabel;
import entities.shampoo.FiftyShades;
import entities.shampoo.FreshNuke;
import entities.shampoo.PinkPanther;
import entities.shampoo.basic.BasicShampoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        System.out.println();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shampoo_company");
        EntityManager em = emf.createEntityManager();


        BasicShampoo freshNuke = new FreshNuke();
        BasicShampoo pinkPanther = new PinkPanther();
        BasicShampoo fiftyShades = new FiftyShades();

        BasicIngredient am = new AmoniumChloride();
        BasicIngredient lavander = new Lavender();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();
        BasicIngredient strawberry = new Strawberry();

        BasicLabel label1 = new ClassicLabel1("Fresh Nuke", "It’s made of Mint, Nettle and Ammonium Chloride");
        BasicLabel label2 = new ClassicLabel1("pinkPanther", "It’s made of Lavender and Nettle");
        BasicLabel label3 = new ClassicLabel1("FiftyShades", "It’s made of Strawberry and Nettle");


        em.getTransaction().begin();

        freshNuke.setBasicLabel(label1);
        //      pinkPanther.setBasicLabel(label2);
//        fiftyShades.setBasicLabel(label3);
//        em.persist(pinkPanther);
//        em.persist(fiftyShades);
        freshNuke.getIngredients().add(mint);
        freshNuke.getIngredients().add(nettle);
        freshNuke.getIngredients().add(am);

        em.persist(freshNuke);

        em.getTransaction().commit();
        em.close();
        emf.close();


    }
}
