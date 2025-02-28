import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {

    // Create
    public void createMatch(int tournamentId, int teamRadiantId, int teamDireId, int bestOf, String result, String matchDate) {
        String query = "INSERT INTO Match (tournament_id, team_radiant_id, team_dire_id, best_of, result, match_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tournamentId);
            statement.setInt(2, teamRadiantId);
            statement.setInt(3, teamDireId);
            statement.setInt(4, bestOf);
            statement.setString(5, result);
            statement.setDate(6, Date.valueOf(matchDate));
            statement.executeUpdate();
            System.out.println("Match added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding match: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllMatches() {
        String query = "SELECT * FROM Match";
        List<String> matches = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                matches.add(resultSet.getInt("id") + ": " +
                        "(Tournament Id: " + resultSet.getInt("tournament_id") +
                        ", Radiant Id: " + resultSet.getInt("team_radiant_id") +
                        ", Dire Id: " + resultSet.getInt("team_dire_id") +
                        ", Best Of: " + resultSet.getInt("best_of") +
                        ", Result: " + resultSet.getString("result") +
                        ", MatchDate: " + resultSet.getString("match_date") + ")"
                );
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching matches: " + e.getMessage());
        }
        return matches;
    }

    // Update
    public void updateMatch(int id, int tournamentId, int teamRadiantId, int teamDireId, int bestOf, String result, String matchDate) {
        String query = "UPDATE Match SET tournament_id = ?, team_radiant_id = ?, team_dire_id = ?, best_of = ?, reslt = ?, match_date = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tournamentId);
            statement.setInt(2, teamRadiantId);
            statement.setInt(3, teamDireId);
            statement.setInt(4, bestOf);
            statement.setString(5, result);
            statement.setDate(6, Date.valueOf(matchDate));
            statement.setInt(7, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Match updated successfully.");
            } else {
                System.out.println("Match not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating match: " + e.getMessage());
        }
    }

    // Delete
    public void deleteMatch(int id) {
        String query = "DELETE FROM Match WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Match deleted successfully.");
            } else {
                System.out.println("Match not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting Match: " + e.getMessage());
        }
    }
}