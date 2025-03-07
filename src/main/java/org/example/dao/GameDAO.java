package org.example.dao;

import org.example.model.Game;
import org.example.model.GameStatsDTO;
import org.example.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameDAO {

    public List<Game> getGamesByMatchId(int matchId) {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Game WHERE match_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, matchId);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setMatchId(rs.getInt("match_id"));
                game.setDuration(rs.getInt("duration"));
                game.setWinner(rs.getString("winner"));
                game.setStartTime(rs.getTimestamp("start_time"));
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }



    public List<GameStatsDTO> getGameStatsForGame(int gameId)  {
        List<GameStatsDTO> gameStatsList = new ArrayList<>();
        String sql = "SELECT " +
                "gs.id AS game_stats_id, " +
                "gs.kills, " +
                "gs.deaths, " +
                "gs.assists, " +
                "gs.last_hits, " +
                "gs.gold_per_minute, " +
                "gs.xp_per_minute, " +
                "gs.net_worth, " +
                "gs.final_level, " +
                "p.nickname AS player_nickname, " +
                "h.name AS hero_name, " +
                "ARRAY_AGG(i.name) AS items, " +
                "m.id AS match_id, " +
                "m.match_date, " +
                "t_r.name AS radiant_team, " +
                "t_d.name AS dire_team, " +
                "g.winner, " +
                "CASE " +
                "WHEN p.team_id = m.team_radiant_id THEN 'Radiant' " +
                "WHEN p.team_id = m.team_dire_id THEN 'Dire' " +
                "ELSE 'Unknown' " +
                "END AS team " +  // Поле команды
                "FROM GameStats gs " +
                "JOIN PlayerHero ph ON gs.player_hero_id = ph.id " +
                "JOIN Player p ON ph.player_id = p.id " +
                "JOIN Hero h ON ph.hero_id = h.id " +
                "JOIN Game g ON gs.game_id = g.id " +
                "JOIN Match m ON g.match_id = m.id " +
                "LEFT JOIN GameItemStats gis ON gs.id = gis.game_stats_id " +
                "LEFT JOIN Item i ON gis.item_id = i.id " +
                "LEFT JOIN Team t_r ON m.team_radiant_id = t_r.id " +
                "LEFT JOIN Team t_d ON m.team_dire_id = t_d.id " +
                "WHERE g.id = ? " +
                "GROUP BY " +
                "gs.id, " +
                "gs.kills, " +
                "gs.deaths, " +
                "gs.assists, " +
                "gs.last_hits, " +
                "gs.gold_per_minute, " +
                "gs.xp_per_minute, " +
                "gs.net_worth, " +
                "gs.final_level, " +
                "p.nickname, " +
                "h.name, " +
                "m.id, " +
                "m.match_date, " +
                "t_r.name, " +
                "t_d.name, " +
                "g.winner, " +
                "p.team_id;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gameId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    GameStatsDTO stats = new GameStatsDTO();
                    stats.setGameStatsId(rs.getInt("game_stats_id"));
                    stats.setPlayerNickname(rs.getString("player_nickname"));
                    stats.setHeroName(rs.getString("hero_name"));
                    stats.setItems(Arrays.asList((String[]) rs.getArray("items").getArray()));
                    stats.setMatchId(rs.getInt("match_id"));
                    stats.setMatchDate(rs.getDate("match_date"));
                    stats.setRadiantTeam(rs.getString("radiant_team"));
                    stats.setDireTeam(rs.getString("dire_team"));
                    stats.setWinner(rs.getString("winner"));
                    stats.setKills(rs.getInt("kills"));
                    stats.setDeaths(rs.getInt("deaths"));
                    stats.setAssists(rs.getInt("assists"));
                    stats.setLastHits(rs.getInt("last_hits"));
                    stats.setGoldPerMinute(rs.getInt("gold_per_minute"));
                    stats.setXpPerMinute(rs.getInt("xp_per_minute"));
                    stats.setNetWorth(rs.getInt("net_worth"));
                    stats.setFinalLevel(rs.getInt("final_level"));
                    stats.setTeam(rs.getString("team"));
                    gameStatsList.add(stats);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gameStatsList;
    }
}
