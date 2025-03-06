package org.example.model;

import java.sql.Date;

public class Match {
    private int id;
    private int tournamentId;
    private int teamRadiantId;
    private int teamDireId;
    private Integer matchTypeId; // Nullable
    private int bestOf;
    private Date matchDate;
    private String teamRadiantName;
    private String teamDireName;
    private String matchTypeName;

    public Match() {}

    public int getId() {
        return id;
    }
    public int getTournamentId() {
        return tournamentId;
    }
    public int getTeamRadiantId() {
        return teamRadiantId;
    }

    public int getTeamDireId() {
        return teamDireId;
    }

    public Integer getMatchTypeId() {
        return matchTypeId;
    }

    public int getBestOf() {
        return bestOf;
    }

    public Date getMatchDate() {
        return matchDate;
    }
    public String getTeamRadiantName() {
        return teamRadiantName;
    }
    public String getTeamDireName() {
        return teamDireName;
    }
    public String getMatchTypeName() {
        return matchTypeName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }
    public void setTeamRadiantId(int teamRadiantId) {
        this.teamRadiantId = teamRadiantId;
    }

    public void setTeamDireId(int teamDireId) {
        this.teamDireId = teamDireId;
    }

    public void setMatchTypeId(Integer matchTypeId) {
        this.matchTypeId = matchTypeId;
    }

    public void setBestOf(int bestOf) {
        this.bestOf = bestOf;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public void setTeamRadiantName(String teamRadiantName) {
        this.teamRadiantName = teamRadiantName;
    }
    public void setTeamDireName(String teamDireName) {
        this.teamDireName = teamDireName;
    }
    public void setMatchTypeName(String matchTypeName) {
        this.matchTypeName = matchTypeName;
    }
}