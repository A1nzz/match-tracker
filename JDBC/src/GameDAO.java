import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {

    // Create
    public void createGame(int matchId, int duration, String winner, Timestamp startTime) {
        String query = "INSERT INTO Game (match_id, duration, winner, start_time) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, matchId);
            statement.setInt(2, duration);
            statement.setString(3, winner);
            statement.setTimestamp(4, startTime);
            statement.executeUpdate();
            System.out.println("Game added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding game: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllGames() {
        String query = "SELECT * FROM gamewithscores";
        List<String> games = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                games.add(
                        "Match Id: " + resultSet.getInt("match_id") +
                        "Duration: " + resultSet.getInt("duration") +
                        "Winner: " + resultSet.getString("winner") +
                        "Radiant: " + resultSet.getInt("radiant_score") +
                        "Dire: " + resultSet.getString("dire_score") +
                        "StartTime: " + resultSet.getTimestamp("start_time")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching games: " + e.getMessage());
        }
        return games;
    }

    // Update
    public void updateMatch(int id, int matchId, int duration, String winner, Timestamp startTime) {
        String query = "UPDATE Game SET match_id = ?, duration = ?, winner = ?, start_time = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, matchId);
            statement.setInt(2, duration);
            statement.setString(3, winner);
            statement.setTimestamp(4, startTime);
            statement.setInt(5, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Game updated successfully.");
            } else {
                System.out.println("Game not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating game: " + e.getMessage());
        }
    }

    // Delete
    public void deleteGame(int id) {
        String query = "DELETE FROM Game WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Game deleted successfully.");
            } else {
                System.out.println("Game not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting Game: " + e.getMessage());
        }
    }
}