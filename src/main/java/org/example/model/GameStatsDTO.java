package org.example.model;

import java.util.Date;
import java.util.List;

public class GameStatsDTO {
    private int gameStatsId;
    private String playerNickname;
    private String heroName;
    private List<String> items; // Список предметов
    private int matchId;
    private Date matchDate;
    private String radiantTeam;
    private String direTeam;
    private String winner;
    private int kills;
    private int deaths;
    private int assists;
    private int lastHits;
    private int goldPerMinute;
    private int xpPerMinute;
    private int netWorth;
    private int finalLevel;
    private String team;

    // Геттеры и сеттеры

    public int getGameStatsId() {
        return gameStatsId;
    }

    public void setGameStatsId(int gameStatsId) {
        this.gameStatsId = gameStatsId;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getRadiantTeam() {
        return radiantTeam;
    }

    public void setRadiantTeam(String radiantTeam) {
        this.radiantTeam = radiantTeam;
    }

    public String getDireTeam() {
        return direTeam;
    }

    public void setDireTeam(String direTeam) {
        this.direTeam = direTeam;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getLastHits() {
        return lastHits;
    }

    public void setLastHits(int lastHits) {
        this.lastHits = lastHits;
    }

    public int getGoldPerMinute() {
        return goldPerMinute;
    }

    public void setGoldPerMinute(int goldPerMinute) {
        this.goldPerMinute = goldPerMinute;
    }

    public int getXpPerMinute() {
        return xpPerMinute;
    }

    public void setXpPerMinute(int xpPerMinute) {
        this.xpPerMinute = xpPerMinute;
    }

    public int getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(int netWorth) {
        this.netWorth = netWorth;
    }

    public int getFinalLevel() {
        return finalLevel;
    }

    public void setFinalLevel(int finalLevel) {
        this.finalLevel = finalLevel;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}