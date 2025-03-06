package org.example.model;

import java.sql.Timestamp;

public class Game {
    private int id;
    private int matchId;
    private int duration;
    private String winner;
    private Timestamp startTime;

    // Геттеры
    public int getId() {
        return id;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getDuration() {
        return duration;
    }

    public String getWinner() {
        return winner;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    // Сеттеры
    public void setId(int id) {
        this.id = id;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
