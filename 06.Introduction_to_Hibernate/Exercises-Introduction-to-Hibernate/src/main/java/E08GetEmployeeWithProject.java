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

public class E08GetEmployeeWithProject {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        System.out.println("Input: ");
        String[] tokens = new String[1];

        StringBuilder sb = new StringBuilder();

        Employee employee = new Employee();

        try {
            String line = reader.readLine().trim();
            tokens[0] = line;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            em.getTransaction().begin();

            employee = em
                    .createQuery("FROM Employee as e WHERE e.id = :employeeId", Employee.class)
                    .setParameter("employeeId", Integer.parseInt(String.valueOf(tokens[0])))
                    .getSingleResult();

            em.getTransaction().commit();


            sb.append(employee.getFirstName())
                    .append(" ")
                    .append(employee.getLastName())
                    .append(" - ")
                    .append(employee.getJobTitle())
                    .append(System.lineSeparator());

            employee.getProjects().stream()
                    .sorted(Comparator.comparing(Project::getName))
                    .forEach(project -> sb
                            .append("      ")
                            .append(project.getName())
                            .append(System.lineSeparator()));

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
