package usersystemapp.domain.entities.models;

import usersystemapp.domain.entities.enumeratedtypes.BackgroundColor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    private String name;

    @Enumerated
    private BackgroundColor backgroundColor;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

    @ManyToMany(targetEntity = Picture.class)
    @JoinTable(name = "albums_pictures",
            joinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id", referencedColumnName = "id")})
    private Set<Picture> pictures;

    private Boolean isPublic;

    public Album() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BackgroundColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(final BackgroundColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(final Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(final Boolean aPublic) {
        isPublic = aPublic;
    }
}
