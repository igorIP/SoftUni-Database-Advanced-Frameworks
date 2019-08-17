package usersystemapp.services.api;

import org.springframework.stereotype.Service;
import usersystemapp.domain.entities.enumeratedtypes.AgeRestriction;
import usersystemapp.domain.entities.models.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public interface UserService {

    void seedUsers();

    List<User> getAllUsersByEmailProvider(String provider);

    void deactivateInactiveUsers(final Date date);

    void save(final User user);

    Long getUsersCount();

    Integer getCountForUsersRegisteredAfterYear(String stringAsDate);

    List<String> getUserNamesAndAgeByAgeRange(final int lowBound,
                                              final int highBound);

    User getUserByFirstName(String name);

    User getUserByLastName(String name);

    List<User> getUsersByLastNames(Collection<String> names);

    Long countUsersWithSameLastName(String name);

    List<User> getUsersByFirstAndLastNameStartingWith(String param1, String param2);

    List<User> getUsersByAgeBetweenAndBornTown(int lowBound, int highBound, String townName);

    List<User> getUsersByBornTown(List<String> townName);

    List<User> getUsersWithFriendsLessThen(Integer value);

    void removeUserByFirstName(String name);

    //Select Users firstName for given Age Restriction
    List<String> getUsersUsernamesByAgeRestriction(AgeRestriction ageRestriction);

    //Select Users firstName that are registered on before given date :format dd-MM-yyyy
    List<String> getUserUsernameRegisteredBefore(String dateAsString);

    //dto simple
    String getReducedUserByFirstName(User user);

    String incraseUserAgeRegisteredAfter(String dateAsString, String numberToMultiplicate);

    void seedFriends();
}
