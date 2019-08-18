package app.ccb.repositories;

import app.ccb.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findDistinctFirstByFullNameEquals(String fullName);

    @Query(value =
            "select\n" +
                    "    c.*,\n" +
                    "    count(ca.id) as countOfcards\n" +
                    "from clients as c\n" +
                    "join bank_accounts as b on c.id = b.client_id\n" +
                    "join cards as ca on b.id = ca.bank_account_id\n" +
                    "group by c.id\n" +
                    "order by countOfcards desc\n" +
                    "limit 1", nativeQuery = true)
    Client findFamilyGuy();
}
