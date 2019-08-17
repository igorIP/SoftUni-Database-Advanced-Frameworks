package usersystemapp.domain.entities.models;

import usersystemapp.domain.entities.base.BaseEntity;
import usersystemapp.domain.entities.enumeratedtypes.BackgroundColor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Album {

    private long id;
    private String name;
    private BackgroundColor backgroundColor;
    private User user;
    private Set<Picture> pictures;
    private Boolean isPublic;

    public Album() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Enumerated
    public BackgroundColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(final BackgroundColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    //    @JoinTable(name = "albums_users",  option --> 2
//            joinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", // option --> 1
            referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @ManyToMany(targetEntity = Picture.class)
    @JoinTable(name = "albums_pictures",
            joinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id", referencedColumnName = "id")})
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(final Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @Column
    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(final Boolean aPublic) {
        isPublic = aPublic;
    }
}
