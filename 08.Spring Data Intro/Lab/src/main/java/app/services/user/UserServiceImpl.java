package app.services.user;

import app.models.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(final User user) {
        if (this.userRepository.findByUserName(user.getUserName()) != null)
            throw new IllegalArgumentException("User with that name already exists");

        this.userRepository.save(user);
    }
}
