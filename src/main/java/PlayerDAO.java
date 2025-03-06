import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    // Create
    public void createPlayer(String nickname, String realName, Integer teamId, String role, String country) {
        String query = "INSERT INTO Player (nickname, real_name, team_id, role, country) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nickname);
            statement.setString(2, realName);
            statement.setInt(3, teamId);
            statement.setString(4, role);
            statement.setString(5, country);
            statement.executeUpdate();
            System.out.println("Player added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding player: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllPlayers() {
        String query = "SELECT * FROM Player";
        List<String> players = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                players.add(resultSet.getInt("id") + ": " +
                        resultSet.getString("nickname") +
                        " (Team ID: " + resultSet.getInt("team_id") + ", Role: " + resultSet.getString("role") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching players: " + e.getMessage());
        }
        return players;
    }

    // Update
    public void updatePlayer(int id, String nickname, String realName, Integer teamId, String role, String country) {
        String query = "UPDATE Player SET nickname = ?, real_name = ?, team_id = ?, role = ?, country = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nickname);
            statement.setString(2, realName);
            statement.setInt(3, teamId);
            statement.setString(4, role);
            statement.setString(5, country);
            statement.setInt(6, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Player updated successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating player: " + e.getMessage());
        }
    }

    // Delete
    public void deletePlayer(int id) {
        String query = "DELETE FROM Player WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Player deleted successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting player: " + e.getMessage());
        }
    }
}