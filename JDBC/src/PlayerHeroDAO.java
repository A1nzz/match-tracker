import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerHeroDAO {

    // Create
    public void createPlayerHero(int playerId, int heroId, int gamesPlayed, String averagePerfomance) {
        String query = "INSERT INTO PlayerHero (player_id, hero_id, games_played, average_perfomance) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.setInt(2, heroId);
            statement.setInt(3, gamesPlayed);
            statement.setString(4, averagePerfomance);
            statement.executeUpdate();
            System.out.println("PlayerHero added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding playerHero: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllPlayerHeroes() {
        String query = "SELECT * FROM PlayerHero";
        List<String> playerHeroes = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                playerHeroes.add(resultSet.getInt("id") + ": " +
                        "(Player ID: " + resultSet.getInt("player_id") +
                        ", HeroID: " + resultSet.getString("hero_id") +
                        ", Games Played: " + resultSet.getInt("games_played") +
                        ", Average Perfomance" + resultSet.getString("average_perfomance") +
                        ")");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching player heroes: " + e.getMessage());
        }
        return playerHeroes;
    }

    // Update
    public void updatePlayerHero(int id, int playerId, int heroId, int gamesPlayed, String averagePerfomance) {
        String query = "UPDATE PlayerHero SET player_id = ?, heroId = ?, gamesPlayed = ?, averagePerfomance = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             statement.setInt(1, playerId);
             statement.setInt(2, heroId);
             statement.setInt(3, gamesPlayed);
            statement.setString(4, averagePerfomance);
            statement.setInt(5, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Player Hero updated successfully.");
            } else {
                System.out.println("Player hero not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating player hero: " + e.getMessage());
        }
    }

    // Delete
    public void deletePlayerHero(int id) {
        String query = "DELETE FROM PlayerHero WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Player hero deleted successfully.");
            } else {
                System.out.println("Player hero not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting player hero: " + e.getMessage());
        }
    }
}