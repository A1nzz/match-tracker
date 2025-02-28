import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class GameStatsDAO {

    // Create
    public void createGameStats(int gameId, int playerId, int hero_id, int kills, int deaths, int assists, int last_hits, int gold_per_minute, int xp_per_minute, int net_worth) {
        String query = "INSERT INTO GameStats (game_id, player_id, hero_id, kills, deaths, assists, last_hits, gold_per_minute, xp_per_minute, net_worth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gameId);
            statement.setInt(2, playerId);
            statement.setInt(3, hero_id);
            statement.setInt(4, kills);
            statement.setInt(5, deaths);
            statement.setInt(6, assists);
            statement.setInt(7, last_hits);
            statement.setInt(8, gold_per_minute);
            statement.setInt(9, xp_per_minute);
            statement.setInt(10, net_worth);
            statement.executeUpdate();
            System.out.println("Game stat added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding game stat: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllGameStats() {
        String query = "SELECT * FROM GameStats";
        List<String> gameStats = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                gameStats.add(resultSet.getInt("id") + ": " +
                        "( Game Id: " + resultSet.getInt("game_id") +
                        ", Player Id: " + resultSet.getInt("player_id") +
                        ", Hero Id: " + resultSet.getInt("hero_id") +
                        ", Kills: " + resultSet.getInt("kills") +
                        ", Deaths: " + resultSet.getInt("deaths") +
                        ", Assists: " + resultSet.getInt("assists") +
                        ", Last Hits: " + resultSet.getInt("last_hits") +
                        ", GPM: " + resultSet.getInt("gold_per_minute") +
                        ", XPM: " + resultSet.getInt("xp_per_minute") +
                        ", Net worth: " + resultSet.getInt("net_worth")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching game stats: " + e.getMessage());
        }
        return gameStats;
    }

    // Update
    public void updateGameStats(int id, int gameId, int playerId, int hero_id, int kills, int deaths, int assists, int last_hits, int gold_per_minute, int xp_per_minute, int net_worth) {
        String query = "UPDATE GameStats SET gameId = ?, playerId = ?, hero_id = ?, kills = ?, deaths = ?, assists = ?, last_hits = ?, gold_per_minute = ?, xp_per_minute = ?, net_worth  WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gameId);
            statement.setInt(2, playerId);
            statement.setInt(3, hero_id);
            statement.setInt(4, kills);
            statement.setInt(5, deaths);
            statement.setInt(6, assists);
            statement.setInt(7, last_hits);
            statement.setInt(8, gold_per_minute);
            statement.setInt(9, xp_per_minute);
            statement.setInt(10, net_worth);
            statement.setInt(11, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("GameStats updated successfully.");
            } else {
                System.out.println("GameStats not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating game stats: " + e.getMessage());
        }
    }

    // Delete
    public void deleteGameStats(int id) {
        String query = "DELETE FROM GameStats WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("GameStats deleted successfully.");
            } else {
                System.out.println("GameStats not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting Stats: " + e.getMessage());
        }
    }
}