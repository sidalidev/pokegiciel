package com.sid.pokegiciel.model;


import javax.persistence.*;

@Entity
@Table(name = "fights")
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String opponentOne;
    private String opponentTwo;
    private String winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpponentOne() {
        return opponentOne;
    }

    public void setOpponentOne(String opponentOne) {
        this.opponentOne = opponentOne;
    }

    public String getOpponentTwo() {
        return opponentTwo;
    }

    public void setOpponentTwo(String opponentTwo) {
        this.opponentTwo = opponentTwo;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
