package usersystemapp.domain.entities.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    private String title;

    @Column(nullable = false)
    @Size(min = 1, max = 30)
    private String caption;

    @Column
    private String path;

    @ManyToMany(mappedBy = "pictures")
    private Set<Album> albums;

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final Set<Album> albums) {
        this.albums = albums;
    }
}
