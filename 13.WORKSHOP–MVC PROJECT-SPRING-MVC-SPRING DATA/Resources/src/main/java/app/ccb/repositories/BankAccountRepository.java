package app.ccb.repositories;

import app.ccb.domain.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    Optional<BankAccount> findFirstByAccountNumberEquals(String accountNumber);
}
