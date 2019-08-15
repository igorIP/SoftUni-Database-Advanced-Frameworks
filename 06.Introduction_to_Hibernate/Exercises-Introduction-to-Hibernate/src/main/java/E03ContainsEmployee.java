
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class E03ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        System.out.println("Input: ");
        String[] tokens = null;
        try {
            String line = reader.readLine().trim();
            tokens = line.split("\\s+");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Employee> employeeList =
                em.createQuery("FROM Employee as e WHERE e.firstName = :firstName and e.lastName = :lastName", Employee.class)
                        .setParameter("firstName", tokens[0])
                        .setParameter("lastName", tokens[1])
                        .getResultList();

        if (employeeList.isEmpty())
            System.out.println("NO");
        else {
            System.out.println("YES");
        }

        em.close();
        emf.close();
    }
}
