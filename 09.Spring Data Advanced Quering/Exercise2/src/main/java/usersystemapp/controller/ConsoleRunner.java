package usersystemapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import usersystemapp.domain.entities.enumeratedtypes.AgeRestriction;
import usersystemapp.domain.entities.models.User;
import usersystemapp.services.api.*;

import java.util.*;

@SpringBootApplication
//@ComponentScan
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final TownService townService;
    private final CountryService countryService;
    private final AlbumService albumService;
    private final PictureService pictureService;


    @Autowired
    public ConsoleRunner(final UserService userService,
                         final TownService townService,
                         final CountryService countryService,
                         final AlbumService albumService,
                         final PictureService pictureService) {
        this.userService = userService;
        this.townService = townService;
        this.countryService = countryService;
        this.albumService = albumService;
        this.pictureService = pictureService;
    }


    @Override
    public void run(String... args) throws Exception {
        Collection<String> lastNames = new ArrayList<>();
        lastNames.add("Scott");
        lastNames.add("Morales");

//        this.countryService.seedCountries();
//        this.townService.seedTowns();
//        this.userService.seedUsers();
//        this.userService.getUsersByFirstAndLastNameStartingWith("Randy", "Morales");
//        this.userService.getUsersByAgeBetweenAndBornTown(18, 33, "Beverly Hills");
//        this.userService.getAllUsersByEmailProvider("someaddress@emaila.com");
//        this.userService.getUserByFirstName("Randy");
//        this.userService.getUserByLastName("Morales");
//        this.userService.getUsersByLastNames(lastNames);
//        this.userService.countUsersWithSameLastName("Morales");
//        this.userService.getUsersWithFriendsLessThen(1);
//        this.userService.removeUserByFirstName("Susan");
//        this.userService.getUsersByBornTown(townNames);
//        this.userService.getUsersUsernamesByAgeRestriction(AgeRestriction.ADULT);
        //this.userService.getUserUsernameRegisteredBefore("12-04-2020");
        //this.userService.getReducedUserByFirstName(new User());
        this.userService.getCountForUsersRegisteredAfterYear("12-Oct-1995");
        this.userService.incraseUserAgeRegisteredAfter("12-Oct-1995","2");

    }
}

