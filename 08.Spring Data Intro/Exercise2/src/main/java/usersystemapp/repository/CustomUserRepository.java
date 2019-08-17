package usersystemapp.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import usersystemapp.domain.entities.models.User;

//This is a "Base" repository, and is intended just to be extended with other repositories
@NoRepositoryBean
public interface CustomUserRepository<T extends User> extends Repository<T, Long> {
    void create(User user);
}
