package usersystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usersystemapp.domain.entities.enumeratedtypes.AgeRestriction;
import usersystemapp.domain.entities.models.User;
import usersystemapp.domain.entities.models.Town;
import usersystemapp.dto.user.ReducedUser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*
    Querying
    1.Retrieving Data by Custom Queries
     */
    User findFirstByFirstNameEquals(String name);

    User findFirstByLastNameEquals(String name);

    Integer countUsersByRegisteredOnAfter(LocalDate date);

    List<User> findByEmailEndingWith(final String end);

    List<User> findAllByLastNameInOrderByIdDesc(final Collection<String> names);

    Long countAllByLastNameIn(final String name);

    List<User> findBylastTimeLoggedInBefore(final Date date);

    List<User> findAllByAgeBetween(int lowBound, int highBound);

    List<User> findByFirstNameIsStartingWithOrLastNameStartingWith(
            String firstNameStarting, String lastNameStarting);

    List<User> findByAgeBetweenAndBornTownName(int lowBound, int highBound, String townName);

    List<User> findAllByBornTownName(Town town);

    //Select Users firstName for given Age Restriction
    List<User> findAllByAgeRestrictionEquals(AgeRestriction ageRestriction);

    //Select Users firstName that are registered on before given date :format dd-MM-yyyy
    List<User> findAllByRegisteredOnBefore(LocalDate date);

    //dto simple
    ReducedUser findFirstByFirstNameEquals(User name);


    /*
    2.-Java Persistence Query Language
    JPQL Querying
     */
    @Query(value = "select u from User as u where u.firstName like %?1")
    List<Object> findByFirstName(String name);

    //@Query(value = "select u from User u where= :lastName")
    Object findFirstByLastName(@Param(value = "lastName") String lastName);

    @Query(value = "select u from User u join  u.bornTown bt where bt.name like :name")
    List<Object> findUsersByTownName(@Param(value = "name") String name);

    //Select Users by towns list use JPQL
    @Query(value = "select u " +
            "from User as u " +
            "join u.bornTown as t " +
            "where t.name in :values")
    List<User> findByBornTown(@Param(value = "values") List<Town> towns);

    //Select Users with less then 3 friends
    @Query(value = " select u " +
            "from User u " +
            "where u.friends.size < :value")
    List<User> findByCountOfFriendsLessThen(@Param(value = "value") Integer value);

    //Delete Users by given name
    @Modifying
    @Transactional
    @Query(value = " delete  " +
            "from User u " +
            "where u.firstName like :value")
    void deleteUserByFirstName(@Param(value = "value") String value);

    //Update Users age by given number
    @Modifying
    @Transactional
    @Query(value = " update  " +
            "User u " +
            "set u.age = u.age *  :value ")
    void updateUserAgeBy10Percent(@Param(value = "value") BigDecimal value);

    @Transactional
    @Modifying
    @Query(value = "update" +
            " User u" +
            " set u.age = u.age * :num" +
            " where u.registeredOn >= :date ")
    Integer updateUsersAgeAfterDate(@Param(value = "date") LocalDate date,@Param(value = "num") Integer num);

}
