import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    // Create
    public void createItem(String name, int cost, String description) {
        String query = "INSERT INTO Item (name, cost, description) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, cost);
            statement.setString(3, description);
            statement.executeUpdate();
            System.out.println("Item added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding item: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllItems() {
        String query = "SELECT * FROM Item";
        List<String> items = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                items.add(resultSet.getInt("id") + ": " +
                        resultSet.getString("name") + " (Cost: " +
                        resultSet.getInt("cost") + ", Description: " +
                        resultSet.getString("description") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching items: " + e.getMessage());
        }
        return items;
    }

    // Update
    public void updateItem(int id, String newName, int newCost, String newDescription) {
        String query = "UPDATE Item SET name = ?, cost = ?, description = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setInt(2, newCost);
            statement.setString(3, newDescription);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Item updated successfully.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating item: " + e.getMessage());
        }
    }

    // Delete
    public void deleteItem(int id) {
        String query = "DELETE FROM Item WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Item deleted successfully.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting item: " + e.getMessage());
        }
    }
}