package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB", length = 50000)
    private Byte[] Logo;

    @Column(name = "initials", columnDefinition = "VARCHAR(3)")
    private String initials;

    @OneToOne(targetEntity = Color.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_kit_id", referencedColumnName = "id")
    private Color primaryKitColor;

    @OneToOne(targetEntity = Color.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "secondary_kit_id", referencedColumnName = "id")
    private Color secondaryKitColor;

    @ManyToOne(targetEntity = Town.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town Town;

    @Column(name = "budget", nullable = false)
    private BigDecimal budget;

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte[] getLogo() {
        return Logo;
    }

    public void setLogo(Byte[] logo) {
        Logo = logo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryKitColor() {
        return primaryKitColor;
    }

    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }

    public Color getSecondaryKitColor() {
        return secondaryKitColor;
    }

    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }

    public entities.Town getTown() {
        return Town;
    }

    public void setTown(entities.Town town) {
        Town = town;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
