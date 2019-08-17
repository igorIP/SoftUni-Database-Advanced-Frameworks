package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bet_games")
public class BetGame implements Serializable {

    @Id
    @OneToOne
    private Game game;

    @Id
    @OneToOne
    private Bet bet;

    @OneToOne
    @JoinColumn(name = "result_prediction")
    private ResultPrediction ResultPrediction;//todo (PK = Game + Bet)

    public BetGame() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public entities.ResultPrediction getResultPrediction() {
        return ResultPrediction;
    }

    public void setResultPrediction(entities.ResultPrediction resultPrediction) {
        ResultPrediction = resultPrediction;
    }
}
