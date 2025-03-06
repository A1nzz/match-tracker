import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    // Create
    public void createTeam(String name, String region, double rating, String logoUrl) {
        String query = "INSERT INTO Team (name, region, rating, logo_url) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, region);
            statement.setDouble(3, rating);
            statement.setString(4, logoUrl);
            statement.executeUpdate();
            System.out.println("Team added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding team: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllTeams() {
        String query = "SELECT * FROM Team";
        List<String> teams = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                teams.add(resultSet.getInt("id") + ": " +
                        resultSet.getString("name") +
                        " (" + resultSet.getString("region") + ", Rating: " + resultSet.getDouble("rating") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching teams: " + e.getMessage());
        }
        return teams;
    }

    // Update
    public void updateTeam(int id, String name, String region, double rating, String logoUrl) {
        String query = "UPDATE Team SET name = ?, region = ?, rating = ?, logo_url = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, region);
            statement.setDouble(3, rating);
            statement.setString(4, logoUrl);
            statement.setInt(5, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Team updated successfully.");
            } else {
                System.out.println("Team not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating team: " + e.getMessage());
        }
    }

    // Delete
    public void deleteTeam(int id) {
        String query = "DELETE FROM Team WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Team deleted successfully.");
            } else {
                System.out.println("Team not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting team: " + e.getMessage());
        }
    }
}