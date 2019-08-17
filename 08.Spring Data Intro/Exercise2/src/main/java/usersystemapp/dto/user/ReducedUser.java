package usersystemapp.dto.user;


public class ReducedUser {

    private  String username;
    private  String email;
    //private  String bornTownName;
    private  Integer age;

//    public ReducedUser() {
//    }
//
//        public ReducedUser(final String username,
//                       final String email,
//                       final String townName,
//                       final Integer age) {
//        this.username = username;
//        this.email = email;
//        this.townName = townName;
//        this.age = age;
//    }

    @Override
    public String toString() {
        return "ReducedUser{" +
                "Username='" + username + '\'' +
                ", eamil='" + email + '\'' +
               // ", bornTown='" + bornTownName + '\'' +
                ", age=" + age +
                '}';
    }
}
