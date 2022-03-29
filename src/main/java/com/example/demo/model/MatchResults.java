package com.example.demo.model;

import javax.persistence.*;

@Entity
public class MatchResults {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long playerId;

    @Column(nullable = false)
    private Long opponentId;

    @Column(nullable = false)
    private int[][] results;

    @Column(nullable = false)
    private int[] aces;

    @Column(nullable = false)
    private int[] smashedRackets;

    @Column(nullable = false)
    private int[] doubleFaults;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(Long opponentId) {
        this.opponentId = opponentId;
    }

    public int[][] getResults() {
        return results;
    }

    public void setResults(int[][] results) {
        this.results = results;
    }

    public int[] getAces() {
        return aces;
    }

    public void setAces(int[] aces) {
        this.aces = aces;
    }

    public int[] getSmashedRackets() {
        return smashedRackets;
    }

    public void setSmashedRackets(int[] smashedRackets) {
        this.smashedRackets = smashedRackets;
    }

    public int[] getDoubleFaults() {
        return doubleFaults;
    }

    public void setDoubleFaults(int[] doubleFaults) {
        this.doubleFaults = doubleFaults;
    }
}
