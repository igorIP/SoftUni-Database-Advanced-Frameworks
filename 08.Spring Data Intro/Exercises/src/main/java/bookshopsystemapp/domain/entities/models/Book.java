package bookshopsystemapp.domain.entities.models;

import bookshopsystemapp.domain.entities.EnumeratedTypes.AgeRestrictionType;
import bookshopsystemapp.domain.entities.EnumeratedTypes.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    private String title;
    private EditionType editionType;
    private BigDecimal price;
    private Integer copies;
    private LocalDate releaseDate;
    private AgeRestrictionType ageRestriction;
    private Author author;
    private Set<Category> categories;

    public Book() {
    }

    @Column(name = "title", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "copies", nullable = false)
    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    @Column(name = "releases_date", nullable = false)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction_type", nullable = false)
    public AgeRestrictionType getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestrictionType ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id",
            referencedColumnName = "id",
            nullable = false)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
