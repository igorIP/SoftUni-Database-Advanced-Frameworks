import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E02RemoveObjects {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        try {
            List<Town> towns = em
                    .createQuery("FROM Town as t ", Town.class)
                    .getResultList();

            for (Town town : towns) {
                if (town.getName().length() > 5) {
                    em.detach(town);
                }
            }

            em.getTransaction().begin();

            for (Town town : towns) {
                if (em.contains(town)) {
                    System.out.println(town.getName());
                    town.setName(town.getName().toLowerCase());
                    System.out.println(town.getName());
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
