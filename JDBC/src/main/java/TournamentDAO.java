import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAO {
    public void createTournament(String name, String startDate, String endDate, double prizePool, String organizer) {
        String query = "INSERT INTO Tournament (name, start_date, end_date, prize_pool, organizer) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDate(2, Date.valueOf(startDate));
            statement.setDate(3, Date.valueOf(endDate));
            statement.setDouble(4, prizePool);
            statement.setString(5, organizer);
            statement.executeUpdate();
            System.out.println("Tournament added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding tournament: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllTournaments() {
        String query = "SELECT * FROM Tournament";
        List<String> tournaments = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                tournaments.add(resultSet.getInt("id") + ": " +
                        resultSet.getString("name") + " (" +
                        resultSet.getDate("start_date") + " - " +
                        resultSet.getDate("end_date") + ", " +
                        resultSet.getString("organizer")+ ")");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching tournaments: " + e.getMessage());
        }
        return tournaments;
    }

    // Update
    public void updateTournament(int id, String name, String startDate, String endDate, double prizePool, String organizer) {
        String query = "UPDATE Tournament SET name = ?, start_date = ?, end_date = ?, prize_pool = ?, organizer = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDate(2, Date.valueOf(startDate));
            statement.setDate(3, Date.valueOf(endDate));
            statement.setDouble(4, prizePool);
            statement.setString(5, organizer);
            statement.setInt(6, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Tournament updated successfully.");
            } else {
                System.out.println("Tournament not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating tournament: " + e.getMessage());
        }
    }

    // Delete
    public void deleteTournament(int id) {
        String query = "DELETE FROM Tournament WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Tournament deleted successfully.");
            } else {
                System.out.println("Tournament not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting tournament: " + e.getMessage());
        }
    }


}
