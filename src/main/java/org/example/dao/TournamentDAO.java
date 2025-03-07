package org.example.dao;

import org.example.model.Tournament;
import org.example.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TournamentDAO {
    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        String sql = "SELECT * FROM Tournament";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Tournament tournament = new Tournament();
                tournament.setId(rs.getInt("id"));
                tournament.setName(rs.getString("name"));
                tournament.setStartDate(rs.getDate("start_date"));
                tournament.setEndDate(rs.getDate("end_date"));
                tournament.setPrizePool(rs.getDouble("prize_pool"));
                tournament.setOrganizer(rs.getString("organizer"));
                tournaments.add(tournament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournaments;
    }

    public void addTournament(Tournament tournament) {
        String sql = "INSERT INTO Tournament (name, start_date, end_date, prize_pool, organizer) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, tournament.getName());
            statement.setDate(2, tournament.getStartDate());
            statement.setDate(3, tournament.getEndDate());
            statement.setDouble(4, tournament.getPrizePool());
            statement.setString(5, tournament.getOrganizer());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка исключения, например, выбросить пользовательское исключение
        }
    }

    public void updateTournament(Tournament tournament) {
        String query = "UPDATE Tournament SET name = ?, start_date = ?, end_date = ?, prize_pool = ?, organizer = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(tournament.getStartDate().toString()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(tournament.getEndDate().toString()));
            preparedStatement.setDouble(4, tournament.getPrizePool());
            preparedStatement.setString(5, tournament.getOrganizer());
            preparedStatement.setInt(6, tournament.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTournament(int tournamentId) {
        String query = "DELETE FROM Tournament WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, tournamentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}