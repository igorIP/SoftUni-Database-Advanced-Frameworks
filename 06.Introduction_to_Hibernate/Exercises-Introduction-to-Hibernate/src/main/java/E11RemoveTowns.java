import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

public class E11RemoveTowns {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        System.out.println("Input: ");
        String[] tokens = new String[1];

        StringBuilder sb = new StringBuilder();

        Town town = new Town();

        try {
            String line = reader.readLine().trim();
            tokens[0] = line;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            em.getTransaction().begin();

            List<Address> addresses = em
                    .createQuery("FROM Address as a WHERE a.town.name = :townName", Address.class)
                    .setParameter("townName", tokens[0])
                    .getResultList();

            town = em
                    .createQuery("FROM Town as t WHERE t.name = :townName", Town.class)
                    .setParameter("townName", tokens[0])
                    .getSingleResult();

            String result = String.format("%d address%s in %s deleted",
                    addresses.size(),
                    addresses.size() > 1 ? "es" : "",
                    town.getName());

            for (Address address : addresses) {
                address.getEmployees()
                        .forEach(employee -> employee.setAddress(null));
                address.setTown(null);
                em.remove(address);
            }

            em.remove(town);

            em.getTransaction().commit();

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
