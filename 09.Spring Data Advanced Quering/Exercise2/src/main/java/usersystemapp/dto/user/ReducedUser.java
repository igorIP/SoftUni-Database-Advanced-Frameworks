package usersystemapp.dto.user;

public class ReducedUser {

    private final String Username;
    private final String email;
    private final String bornTown;
    private final Integer age;

    public ReducedUser(final String username,
                       final String email,
                       final String bornTown,
                       final Integer age) {
        Username = username;
        this.email = email;
        this.bornTown = bornTown;
        this.age = age;
    }

    @Override
    public String toString() {
        return "ReducedUser{" +
                "Username='" + Username + '\'' +
                ", eamil='" + email + '\'' +
                ", bornTown='" + bornTown + '\'' +
                ", age=" + age +
                '}';
    }
}
