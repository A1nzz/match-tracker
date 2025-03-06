package org.example.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.model.Match;
import org.example.utils.DBConnection;
import org.example.model.GameStatsDTO;


public class MatchDAO {


    public List<Match> getAllMatches() {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM Match";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt("id"));
                match.setTournamentId(rs.getInt("tournament_id"));
                match.setTeamRadiantId(rs.getInt("team_radiant_id"));
                match.setTeamDireId(rs.getInt("team_dire_id"));
                match.setMatchTypeId(rs.getObject("match_type_id", Integer.class));
                match.setBestOf(rs.getInt("best_of"));
                match.setMatchDate(rs.getDate("match_date"));
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List<Match> getMatchesByTournament(int tournamentId) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT m.id, m.team_radiant_id, m.team_dire_id, t_r.name AS teamRadiantName, t_d.name AS teamDireName, mt.type_name AS matchTypeName, m.match_date, m.best_of " +
                "FROM Match m " +
                "JOIN Team t_r ON m.team_radiant_id = t_r.id " +
                "JOIN Team t_d ON m.team_dire_id = t_d.id " +
                "JOIN MatchType mt ON m.match_type_id = mt.id " +
                "WHERE m.tournament_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setInt(1, tournamentId);

             ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt("id"));
                match.setTeamRadiantName(rs.getString("teamRadiantName"));
                match.setTeamDireName(rs.getString("teamDireName"));
                match.setMatchTypeName(rs.getString("matchTypeName"));
                match.setMatchDate(rs.getDate("match_date"));
                match.setBestOf(rs.getInt("best_of"));
                match.setTeamRadiantId(rs.getInt("team_radiant_id"));
                match.setTeamDireId(rs.getInt("team_dire_id"));
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public String getTournamentName(int tournamentId) {
        String sql = "SELECT name FROM Tournament WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tournamentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public Match getMatchById(int matchId){
        Match match = null;
        String sql = "SELECT * FROM Match WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matchId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    match = new Match();
                    match.setId(rs.getInt("id"));
                    match.setTournamentId(rs.getInt("tournament_id"));
                    match.setTeamRadiantId(rs.getInt("team_radiant_id"));
                    match.setTeamDireId(rs.getInt("team_dire_id"));
                    match.setMatchDate(rs.getDate("match_date"));
                    match.setBestOf(rs.getInt("best_of"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }
}