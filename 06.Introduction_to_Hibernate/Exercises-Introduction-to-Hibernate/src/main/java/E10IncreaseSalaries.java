import entities.Department;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class E10IncreaseSalaries {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        StringBuilder sb = new StringBuilder();

        List<Employee> employees = new ArrayList<>();

        try {
            em.getTransaction().begin();

            employees = em
                    .createQuery("FROM Employee AS e WHERE e.department.name IN (:Engineering, :ToolDesign, :Marketing,:InformationServices)", Employee.class)
                    .setParameter("Engineering", "Engineering")
                    .setParameter("ToolDesign", "Tool Design")
                    .setParameter("Marketing", "Marketing")
                    .setParameter("InformationServices", "Information Services")
                    .getResultList();

            em.getTransaction().commit();

            for (Employee employee : employees) {
                employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(0.12)));
                sb.append(employee.getFirstName())
                        .append(" ")
                        .append(employee.getLastName())
                        .append(" ")
                        .append(employee.getFirstName())
                        .append(" ")
                        .append("(")
                        .append("$")
                        .append(employee.getSalary())
                        .append(")")
                        .append(System.lineSeparator());
            }

            System.out.println(sb.toString().trim());

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
