package usersystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystemapp.domain.entities.models.Town;
import usersystemapp.domain.entities.models.User;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    Town findFirstByNameEquals(String name);
}
