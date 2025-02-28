import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameItemStatsDAO {

    // Create
    public void createGameItemStats(int gameStatsId, int itemId, int quantity) {
        String query = "INSERT INTO GameItemStats (game_stats_id, item_id, quantity) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gameStatsId);
            statement.setInt(2, itemId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
            System.out.println("Game item stats added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding game item stats: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllGameItemStats() {
        String query = "SELECT * FROM GameItemStats";
        List<String> gameItemStats = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                gameItemStats.add(resultSet.getInt("id") + ": " +
                        "(Game Stats ID: " + resultSet.getInt("game_stats_id") +
                        ", Item ID: " + resultSet.getInt("item_id") +
                        ", Quantity: " + resultSet.getInt("quantity") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching game item stats: " + e.getMessage());
        }
        return gameItemStats;
    }

    // Update
    public void updateGameItemStats(int id, int newGameStatsId, int newItemId, int newQuantity) {
        String query = "UPDATE GameItemStats SET game_stats_id = ?, item_id = ?, quantity = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newGameStatsId);
            statement.setInt(2, newItemId);
            statement.setInt(3, newQuantity);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Game item stats updated successfully.");
            } else {
                System.out.println("Game item stats not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating game item stats: " + e.getMessage());
        }
    }

    // Delete
    public void deleteGameItemStats(int id) {
        String query = "DELETE FROM GameItemStats WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Game item stats deleted successfully.");
            } else {
                System.out.println("Game item stats not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting game item stats: " + e.getMessage());
        }
    }
}