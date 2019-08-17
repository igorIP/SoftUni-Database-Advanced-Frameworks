package usersystemapp.domain.entities.models;

import org.springframework.format.annotation.DateTimeFormat;
import usersystemapp.annotations.email.Email;
import usersystemapp.annotations.password.Password;
import usersystemapp.constants.TextConstants;
import usersystemapp.domain.entities.enumeratedtypes.AgeRestriction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    private String lastName;

//    @Column(nullable = false)
//    @Size(min = 1, max = 30)
//    private String fullName;

    @Column(nullable = false)
    @Size(min = 4, max = 30)
    private String username;

    /*
Here, we’ll focus on the different types of method constraints such as:
1.single-parameter constraints
2.cross-parameter
3.return constraints
     */
    @Column(nullable = false)
    @Password(minLength = 6,
            maxLength = 50,
            containsDigit = true,
            containsLowerCase = true,
            containsUpperCase = true,
            containsSpecialSymbols = true)
    private String password;


    @Column(unique = true)
    @NotNull//single-parameter constraints
    @Email()//custom cross-parameter constraint
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Album> albums;

    @Column()
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate registeredOn;

    @Column()
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate lastTimeLoggedIn;

    @Column()
    @Min(value = 1, message = TextConstants.AGE_TOO_LOW)
    @Max(value = 120, message = TextConstants.AGE_TOO_HIGH)
    private Integer age;

    @Lob
    @Size(max = 1024 * 1024)
    private byte[] profilePicture;

    @Column()
    private Boolean isDeleted;

    @Enumerated
    private AgeRestriction ageRestriction;

    @OneToOne(targetEntity = Town.class)
    @JoinColumn(name = "born_town",
            referencedColumnName = "id")
    private Town bornTown;

    @OneToOne(targetEntity = Town.class)
    @JoinColumn(name = "current_town",
            referencedColumnName = "id")
    private Town currentlyLivingTown;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id", referencedColumnName = "id")})
    private Set<User> friends;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final Set<Album> albums) {
        this.albums = albums;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(final LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDate getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(final LocalDate lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(final byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(final Boolean deleted) {
        isDeleted = deleted;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(final Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public void setCurrentlyLivingTown(final Town currentlyLivingTown) {
        this.currentlyLivingTown = currentlyLivingTown;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(final Set<User> friends) {
        this.friends = friends;
    }
}
