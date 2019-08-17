package entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private BigInteger betMoney;

    @Basic
    private Date timeOfBet;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<User> users;

    public Bet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(BigInteger betMoney) {
        this.betMoney = betMoney;
    }

    public Date getTimeOfBet() {
        return timeOfBet;
    }

    public void setTimeOfBet(Date timeOfBet) {
        this.timeOfBet = timeOfBet;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
