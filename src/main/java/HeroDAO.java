import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {

    // Create
    public void createHero(String name, String primaryAttribute, String attackType, String roles) {
        String query = "INSERT INTO Hero (name, primary_attribute, attack_type, roles) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, primaryAttribute);
            statement.setString(3, attackType);
            statement.setString(4, roles);
            statement.executeUpdate();
            System.out.println("Hero added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding hero: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllHeroes() {
        String query = "SELECT * FROM Hero";
        List<String> heroes = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                heroes.add(resultSet.getInt("id") + ": " +
                        resultSet.getString("name") +
                        " (Attribute: " + resultSet.getString("primary_attribute") +
                        ", Attack Type: " + resultSet.getString("attack_type") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching heroes: " + e.getMessage());
        }
        return heroes;
    }

    // Update
    public void updateHero(int id, String name, String primaryAttribute, String attackType, String roles) {
        String query = "UPDATE Hero SET name = ?, primary_attribute = ?, attack_type = ?, roles = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, primaryAttribute);
            statement.setString(3, attackType);
            statement.setString(4, roles);
            statement.setInt(5, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Hero updated successfully.");
            } else {
                System.out.println("Hero not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating hero: " + e.getMessage());
        }
    }

    // Delete
    public void deleteHero(int id) {
        String query = "DELETE FROM Hero WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Hero deleted successfully.");
            } else {
                System.out.println("Hero not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting hero: " + e.getMessage());
        }
    }
}