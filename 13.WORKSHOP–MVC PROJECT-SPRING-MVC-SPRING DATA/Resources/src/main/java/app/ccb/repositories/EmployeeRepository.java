package app.ccb.repositories;

import app.ccb.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findFirstByFirstNameEqualsAndLastNameEquals(String firstName, String lastName);

    @Query(value =
            "select e " +
                    "from Employee as e " +
                    "where e.clients.size > 0 " +
                    "order by e.clients.size desc, " +
                    "e.id")
    List<Employee> findTopEmployees();

}
