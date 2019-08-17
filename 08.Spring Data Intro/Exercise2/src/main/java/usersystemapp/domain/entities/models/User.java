package usersystemapp.domain.entities.models;

import org.springframework.format.annotation.DateTimeFormat;
import usersystemapp.annotations.email.Email;
import usersystemapp.annotations.password.Password;
import usersystemapp.constants.TextConstants;
import usersystemapp.domain.entities.enumeratedtypes.AgeRestriction;
import usersystemapp.domain.entities.enumeratedtypes.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {


    private Long id; //ID changed from long to string
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Set<Album> albums;
    private LocalDate registeredOn;
    private LocalDate lastTimeLoggedIn;
    private Integer age;
    private byte[] profilePicture;
    private Boolean isDeleted;
    private AgeRestriction ageRestriction;// changed it from ordinal to string
    private Town bornTown;
    private Town currentlyLivingTown;
    private Set<User> friends;
    private Role role;//to be spring roles
    private Set<Game> games;
    private Set<Order> orders;
        /*
        for up
Here, we’ll focus on the different types of method constraints such as:
1.single-parameter constraints
2.cross-parameter
3.return constraints
     */
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)//unique = true
    @Size(min = 4, max = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Column(nullable = false)
    @Password(minLength = 6,
            maxLength = 50,
            containsDigit = true,
            containsLowerCase = true,
            containsUpperCase = true,
            containsSpecialSymbols = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Column(unique = true)
    @NotNull//single-parameter constraints
    @Email()//custom cross-parameter constraint
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "user")
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final Set<Album> albums) {
        this.albums = albums;
    }

    @Column()
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(final LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Column()
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDate getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(final LocalDate lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Column()
    @Min(value = 1, message = TextConstants.AGE_TOO_LOW)
    @Max(value = 120, message = TextConstants.AGE_TOO_HIGH)
    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    @Lob
    @Size(max = 1024 * 1024)
    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(final byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Column()
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(final Boolean deleted) {
        isDeleted = deleted;
    }

    @OneToOne(targetEntity = Town.class)
    @JoinColumn(name = "born_town",
            referencedColumnName = "id")
    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(final Town bornTown) {
        this.bornTown = bornTown;
    }

    @OneToOne(targetEntity = Town.class)
    @JoinColumn(name = "current_town",
            referencedColumnName = "id")
    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public void setCurrentlyLivingTown(final Town currentlyLivingTown) {
        this.currentlyLivingTown = currentlyLivingTown;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id", referencedColumnName = "id")})
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(final Set<User> friends) {
        this.friends = friends;
    }

    @Enumerated(EnumType.STRING)
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @Enumerated(value = EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToMany(targetEntity = Game.class)
    @JoinTable(name = "users_games",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")})
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @OneToMany(targetEntity = Order.class)
    public Set<Order> getOrders() {
        return orders;
    }


    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
