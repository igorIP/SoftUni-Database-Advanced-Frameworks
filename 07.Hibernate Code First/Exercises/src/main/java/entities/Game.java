package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @OneToOne(targetEntity = Team.class)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @OneToOne(targetEntity = Team.class)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @Column(name = "home_goals")
    private int homeGoals;

    @Column(name = "away_goals")
    private int awayGoals;

    @Basic
    private Date date;

    @Basic
    private double HomeTeamWinBetRate;

    @Basic
    private double AwayTeamWinBetRate;

    @Basic
    private double DrawGameWinBetRate;

    @ManyToOne(targetEntity = Round.class)
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;

    @ManyToOne(targetEntity = Competition.class)
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    public Game() {
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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getHomeTeamWinBetRate() {
        return HomeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
        HomeTeamWinBetRate = homeTeamWinBetRate;
    }

    public double getAwayTeamWinBetRate() {
        return AwayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
        AwayTeamWinBetRate = awayTeamWinBetRate;
    }

    public double getDrawGameWinBetRate() {
        return DrawGameWinBetRate;
    }

    public void setDrawGameWinBetRate(double drawGameWinBetRate) {
        DrawGameWinBetRate = drawGameWinBetRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
