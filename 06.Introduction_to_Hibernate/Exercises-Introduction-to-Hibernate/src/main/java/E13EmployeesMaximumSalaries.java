import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class E13EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        StringBuilder sb = new StringBuilder();

        try {
            em.getTransaction().begin();

            List<Department> departments = em
                    .createQuery(" FROM Department AS d", Department.class)
                    .getResultList();

            for (Department department : departments) {
                Optional<Employee> maxSalaryDepartment = department.getEmployees().stream()
                        .max(Comparator.comparing(Employee::getSalary));
                System.out.println(department.getName());
                System.out.println(maxSalaryDepartment.get().getSalary());
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
