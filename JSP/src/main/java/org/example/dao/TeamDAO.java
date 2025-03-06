package org.example.dao;

import org.example.model.Team;
import org.example.model.Player;
import org.example.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    public Team getTeamById(int teamId) {
        Team team = null;
        String sql = "SELECT * FROM Team WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    team = new Team();
                    team.setId(rs.getInt("id"));
                    team.setName(rs.getString("name"));
                    team.setRegion(rs.getString("region"));
                    team.setRating(rs.getDouble("rating"));
                    team.setLogoUrl(rs.getString("logo_url"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }

    public List<Player> getPlayersByTeamId(int teamId) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Player WHERE team_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Player player = new Player();
                    player.setId(rs.getInt("id"));
                    player.setNickname(rs.getString("nickname"));
                    player.setRealName(rs.getString("real_name"));
                    player.setRole(rs.getString("role"));
                    player.setCountry(rs.getString("country"));
                    players.add(player);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}
