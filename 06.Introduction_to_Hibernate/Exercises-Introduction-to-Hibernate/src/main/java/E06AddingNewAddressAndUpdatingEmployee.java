import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class E06AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        final String NEW_ADDRESS = "Vitoshka 15";

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        System.out.println("Input: ");
        String[] tokens = new String[1];
        try {
            String line = reader.readLine().trim();
            tokens[0] = line;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            em.getTransaction().begin();
            Employee employee = em.createQuery("FROM Employee as e WHERE e.firstName = :employeeFirstName", Employee.class)
                    .setParameter("employeeFirstName", tokens[0])
                    .getSingleResult();
            em.getTransaction().commit();

            em.getTransaction().begin();
            Town sofia = em
                    .createQuery("SELECT t FROM Town AS t WHERE t.name = 'Sofia'", Town.class)
                    .getSingleResult();
            em.getTransaction().commit();

            Address newAddress = new Address();
            newAddress.setText(NEW_ADDRESS);
            newAddress.setTown(sofia);

            //start
            em.getTransaction().begin();

            em.persist(newAddress);

            System.out.println(em.contains(employee.getAddress()));
            em.detach(employee.getAddress());
            System.out.println(em.contains(employee.getAddress()));
            employee.setAddress(newAddress);
            em.merge(employee);
            System.out.println(em.contains(employee.getAddress()));
            //em.flush();

            em.getTransaction().commit();
            //end

            System.out.printf("%s %s changed the address to %s%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAddress().getText());

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
