package app.ccb.repositories;

import app.ccb.domain.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
