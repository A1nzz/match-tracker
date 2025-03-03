package com.example.dao;

import com.example.model.Tournament;
import com.example.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TournamentDAO {
    private static final Logger logger = LoggerFactory.getLogger(TournamentDAO.class);


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
                tournament.setPrizePool(rs.getInt("prize_pool"));
                tournament.setOrganizer(rs.getString("organizer"));
                tournaments.add(tournament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Fetched {} tournaments", tournaments.size());

        return tournaments;
    }
}