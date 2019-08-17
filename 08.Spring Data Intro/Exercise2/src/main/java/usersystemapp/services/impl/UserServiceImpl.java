package usersystemapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usersystemapp.domain.entities.enumeratedtypes.AgeRestriction;
import usersystemapp.domain.entities.models.Town;
import usersystemapp.domain.entities.models.User;
import usersystemapp.repository.TownRepository;
import usersystemapp.repository.UserRepository;
import usersystemapp.services.api.UserService;
import usersystemapp.util.FileUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;

    private final String USERS_FILE_PATH = "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/08.Spring Data Intro/Exercise2/src/main/resources/users";


    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final TownRepository townRepository,
                           final FileUtil fileUtil) {
        this.userRepository = userRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedUsers() {
        if (this.userRepository.count() != 0) return;

        LongAdder count = new LongAdder();
        count.add(1);

        List<String> data = this.fileUtil.getFileInput(USERS_FILE_PATH);
        for (String line : data) {
            String[] tokens = line.split("\\s+");

            User user = new User();
            LocalDate date = LocalDate.parse(tokens[8], DateTimeFormatter.ofPattern("d/M/yyyy"));
            String firstName = tokens[0];
            String lastName = tokens[1];
            Integer age = Integer.parseInt(tokens[2]);
            Town bornTown = this.townRepository.findById(count.longValue()).get();
            Town livingTown = this.townRepository.findById(count.longValue()).get();
            count.increment();
            String email = tokens[5];
            String username = tokens[6];
            String password = tokens[7];

            user.setFirstName(firstName.trim());
            user.setLastName(lastName.trim());
            user.setAge(age);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setBornTown(bornTown);
            user.setCurrentlyLivingTown(livingTown);
            user.setRegisteredOn(date);
            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void seedFriends() {
        Set<User> toBecomeFriend = new HashSet<>();
        User susan = this.userRepository.findFirstByFirstNameEquals("Susan");

        toBecomeFriend.add(susan);
        this.userRepository.findById(1L).get().setFriends(toBecomeFriend);

        this.userRepository.save(susan);
        System.out.println();
    }

    @Override
    public List<User> getAllUsersByEmailProvider(String provider) {
        return this.userRepository.findByEmailEndingWith(provider);
    }

    @Override
    public void deactivateInactiveUsers(Date date) {
        this.userRepository.findBylastTimeLoggedInBefore(date)
                .forEach(user -> user.setDeleted(true));
    }

    @Override
    public void save(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public Long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public List<String> getUserNamesAndAgeByAgeRange(final int lowBound, final int highBound) {
        return this.userRepository.findAllByAgeBetween(lowBound, highBound)
                .stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .map(user -> String.format("%s %s - %d years old",
                        user.getFirstName(), user.getLastName(), user.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByFirstName(String name) {
        List<Object> user = this.userRepository.findByFirstName(name);
        return (User) user.get(0);
    }

    @Override
    public User getUserByLastName(String name) {
        User user = (User) this.userRepository.findFirstByLastName(name);
        return user;
    }

    @Override
    public List<User> getUsersByFirstAndLastNameStartingWith(String param1, String param2) {
        List<User> result;
        result = this.userRepository.findByFirstNameIsStartingWithOrLastNameStartingWith(param1, param2);
        return result;
    }

    @Override
    public List<User> getUsersByAgeBetweenAndBornTown(int lowBound, int highBound, String townName) {
        List<User> result;
        result = this.userRepository.findByAgeBetweenAndBornTownName(18, 33, townName);
        return result;
    }

    @Override
    public List<User> getUsersByBornTown(List<String> townNames) {
        List<User> result;
        result = this.userRepository.findByBornTown(Arrays.asList(
                this.townRepository.findFirstByNameEquals(townNames.get(0)),
                this.townRepository.findFirstByNameEquals(townNames.get(1))
        ));
        return result;
    }

    @Override
    public List<User> getUsersByLastNames(Collection<String> names) {
        List<User> result;
        result = this.userRepository.findAllByLastNameInOrderByIdDesc(names);
        return result;
    }

    @Override
    public Long countUsersWithSameLastName(String name) {
        Long result;
        result = this.userRepository.countAllByLastNameIn(name);
        return result;
    }

    @Override
    public List<User> getUsersWithFriendsLessThen(Integer value) {
        List<User> result;
        result = this.userRepository.findByCountOfFriendsLessThen(value);
        return result;
    }

    @Override
    public void removeUserByFirstName(String name) {
        this.userRepository.deleteUserByFirstName(name);
    }

    @Override
    public List<String> getUsersUsernamesByAgeRestriction(AgeRestriction ageRestriction) {
        List<User> result = this.userRepository.findAllByAgeRestrictionEquals(ageRestriction);
        return result.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getUserUsernameRegisteredBefore(String dateAsString) {
        LocalDate date = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("dd-MM-yyy"));
        List<String> result = this.userRepository.findAllByRegisteredOnBefore(date)
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public String getReducedUserByFirstName(User user) {
        User randomUser = this.userRepository.findById(1L).get();
        return this.userRepository.
                findFirstByFirstNameEquals(randomUser.getFirstName()).getFirstName();
    }

    @Override
    public Integer getCountForUsersRegisteredAfterYear(String dateAsString) {
        LocalDate date = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        return this.userRepository.countUsersByRegisteredOnAfter(date);
    }

    @Override
    public String incraseUserAgeRegisteredAfter(String dateAsString, String numberToMultiplicate) {
        LocalDate date = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        this.userRepository.updateUsersAgeAfterDate(date, Integer.valueOf(numberToMultiplicate));
        return null;
    }

}
