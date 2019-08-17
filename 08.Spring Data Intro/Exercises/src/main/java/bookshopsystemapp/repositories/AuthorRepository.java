package bookshopsystemapp.repositories;

import bookshopsystemapp.domain.entities.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value =
            "select a from bookshopsystemapp.domain.entities.models.Author as a")
    List<Author> findAuthorsInDesce();
}
