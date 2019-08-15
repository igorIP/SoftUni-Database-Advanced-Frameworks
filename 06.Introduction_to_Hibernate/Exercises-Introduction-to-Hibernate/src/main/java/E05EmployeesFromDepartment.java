import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E05EmployeesFromDepartment {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        final String DEPARTMENT_NAME = "Research and Development";
        final String PARAMETER_DEPARTMENT_NAME = "departmentName";
        final String QUERY_ALL_EMPLOYEES_FROM_RESEARCH_AND_DEVELOPMENT = "FROM Employee AS e WHERE e.department.name = :departmentName ORDER BY e.salary, e.id";

        em.createQuery(QUERY_ALL_EMPLOYEES_FROM_RESEARCH_AND_DEVELOPMENT, Employee.class)
                .setParameter(PARAMETER_DEPARTMENT_NAME, DEPARTMENT_NAME)
                .getResultList()
                .forEach(employee -> System.out.printf("%s %s from %s - %n%.2f%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartment().getName(),
                        employee.getSalary()));

        em.close();
        emf.close();
    }
}