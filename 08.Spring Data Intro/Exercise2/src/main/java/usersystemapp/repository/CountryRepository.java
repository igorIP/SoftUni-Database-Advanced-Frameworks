package usersystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystemapp.domain.entities.models.Country;
import usersystemapp.domain.entities.models.User;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
