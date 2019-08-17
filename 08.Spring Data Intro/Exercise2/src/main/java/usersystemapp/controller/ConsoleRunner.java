package usersystemapp.controller;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import usersystemapp.domain.entities.models.User;
import usersystemapp.dto.user.UserDto;
import usersystemapp.services.api.*;
import usersystemapp.util.ConsoleUtil;

import java.util.List;

@SpringBootApplication
//@ComponentScan
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final TownService townService;
    private final CountryService countryService;
    private final AlbumService albumService;
    private final PictureService pictureService;
    private final PropertyMap<User, UserDto> userMap;
    private final ConsoleUtil consoleUtil;


    @Autowired
    public ConsoleRunner(final UserService userService,
                         final TownService townService,
                         final CountryService countryService,
                         final AlbumService albumService,
                         final PictureService pictureService,
                         final PropertyMap<User, UserDto> userMap,
                         final ConsoleUtil consoleUtil) {
        this.userService = userService;
        this.townService = townService;
        this.countryService = countryService;
        this.albumService = albumService;
        this.pictureService = pictureService;
        this.userMap = userMap;
        this.consoleUtil = consoleUtil;
    }


    @Override
    public void run(String... args) throws Exception {
//        Collection<String> lastNames = new ArrayList<>();
//        lastNames.add("Scott");
//        lastNames.add("Morales");

        this.countryService.seedCountries();
        this.townService.seedTowns();
        this.userService.seedUsers();
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
//        this.userService.getUserUsernameRegisteredBefore("12-04-2020");
//        this.userService.getReducedUserByFirstName(new User());
//        this.userService.getCountForUsersRegisteredAfterYear("12-Oct-1995");
//        this.userService.incraseUserAgeRegisteredAfter("12-Oct-1995","2");


        //Mapping DTO's********************
        ModelMapper modelMapper = new ModelMapper(); //mapper

        User user = this.userService.getUserByLastName("Morales");
        UserDto userDto = new UserDto();


        //modelMapper.addMappings(this.userMap); //pre java 8 mapping
        //pre java 7, 8 config and converting properties in /../mapping/MapperDto.java


        //Explicit Mapping DTO to Entity – Java 8
        TypeMap<User, UserDto> userTypeMap =
                modelMapper.createTypeMap(User.class, UserDto.class);
        modelMapper.validate();

        userTypeMap.addMappings(m -> m.map(User::getFirstName, UserDto::setFirstName));

        userDto = userTypeMap.map(user);
        //**********************************
        //Converting Properties – Java 8
        Converter<String, String> convertToUppercase =
                ctx -> ctx.getSource() == null
                        ? null :
                        ctx.getSource().toUpperCase();

        userTypeMap.addMappings(m -> m.using(convertToUppercase).map(User::getFirstName, UserDto::setFirstName));
        //********************************

        //Implement User Registration, Login and Logout
        List<String> tokens = this.consoleUtil.getConsoleInput();

    }
}

