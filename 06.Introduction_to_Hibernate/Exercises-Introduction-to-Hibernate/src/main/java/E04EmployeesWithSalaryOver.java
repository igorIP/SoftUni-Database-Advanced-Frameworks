import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class E04EmployeesWithSalaryOver {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        BigDecimal minSalary = BigDecimal.valueOf(50000);


        em.createQuery("FROM Employee AS e WHERE e.salary > :salaryMedium", Employee.class)
                .setParameter("salaryMedium", minSalary)
                .getResultList()
                .forEach(employee -> System.out.println(employee.getSalary()));

        em.close();
        emf.close();
    }
}
