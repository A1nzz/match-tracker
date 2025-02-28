import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchTypeDAO {

    // Create
    public void createMatchType(String typeName) {
        String query = "INSERT INTO MatchType (type_name) VALUES (?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, typeName);
            statement.executeUpdate();
            System.out.println("Match type added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding match type: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllMatchTypes() {
        String query = "SELECT * FROM MatchType";
        List<String> matchTypes = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                matchTypes.add(resultSet.getInt("id") + ": " +
                        resultSet.getString("type_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching match types: " + e.getMessage());
        }
        return matchTypes;
    }

    // Update
    public void updateMatchType(int id, String newTypeName) {
        String query = "UPDATE MatchType SET type_name = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newTypeName);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Match type updated successfully.");
            } else {
                System.out.println("Match type not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating match type: " + e.getMessage());
        }
    }

    // Delete
    public void deleteMatchType(int id) {
        String query = "DELETE FROM MatchType WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Match type deleted successfully.");
            } else {
                System.out.println("Match type not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting match type: " + e.getMessage());
        }
    }
}