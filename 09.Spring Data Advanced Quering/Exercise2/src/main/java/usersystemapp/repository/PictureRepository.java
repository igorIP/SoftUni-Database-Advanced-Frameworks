package usersystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystemapp.domain.entities.models.Picture;
import usersystemapp.domain.entities.models.User;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
