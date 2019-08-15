import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

public class E12FindEmployeesByFirstName {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        System.out.println("Input: ");
        String[] tokens = new String[1];

        StringBuilder sb = new StringBuilder();

        try {
            String line = reader.readLine().trim();
            tokens[0] = line + "%";
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            em.getTransaction().begin();

            List<Employee> employees = em
                    .createQuery("FROM Employee AS e WHERE e.firstName  LIKE :employeeName", Employee.class)
                    .setParameter("employeeName", tokens[0])
                    .getResultList();

            em.getTransaction().commit();

            employees.forEach(employee ->
                    sb.append(employee.getFirstName())
                            .append(" ")
                            .append(employee.getLastName())
                            .append(" - ")
                            .append(employee.getJobTitle())
                            .append(" ")
                            .append(" - ")
                            .append(employee.getSalary())
                            .append(System.lineSeparator())
            );

            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
