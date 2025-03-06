import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GameStatsDAO {

    // Create
    public void createGameStats(int gameId, int playerHeroId, int kills, int deaths, int assists, int lastHits, int goldPerMinute, int xpPerMinute, double netWorth, int finalLevel) {
        String query = "INSERT INTO GameStats (game_id, player_hero_id, kills, deaths, assists, last_hits, gold_per_minute, xp_per_minute, net_worth, final_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gameId);
            statement.setInt(2, playerHeroId);
            statement.setInt(3, kills);
            statement.setInt(4, deaths);
            statement.setInt(5, assists);
            statement.setInt(6, lastHits);
            statement.setInt(7, goldPerMinute);
            statement.setInt(8, xpPerMinute);
            statement.setDouble(9, netWorth);
            statement.setInt(10, finalLevel);
            statement.executeUpdate();
            System.out.println("Game stat added successfully.");
            System.out.println(statement);
        } catch (SQLException e) {
            System.out.println("Error while adding game stat: " + e.getMessage());
        }
    }

    // Read
    public List<String> getAllGameStats() {
        String query = """
        SELECT
                gs.id AS game_stats_id,
                p.nickname AS player_nickname,
                h.name AS hero_name,
                ARRAY_AGG(i.name) AS items,  -- Собранные предметы
                m.id AS match_id,
                m.match_date,
                t_r.name AS radiant_team,
                t_d.name AS dire_team,
                g.winner
        FROM
        GameStats gs
        JOIN
        PlayerHero ph ON gs.player_hero_id = ph.id
        JOIN
        Player p ON ph.player_id = p.id
        JOIN
        Hero h ON ph.hero_id = h.id
        JOIN
        Game g ON gs.game_id = g.id
        JOIN
        Match m ON g.match_id = m.id
        LEFT JOIN
        GameItemStats gis ON gs.id = gis.game_stats_id
        LEFT JOIN
        Item i ON gis.item_id = i.id
        LEFT JOIN
        Team t_r ON m.team_radiant_id = t_r.id
        LEFT JOIN
        Team t_d ON m.team_dire_id = t_d.id
        GROUP BY
        gs.id, p.nickname, h.name, m.id, t_r.name, t_d.name, g.winner
        """;
    List<String> gameStats = new ArrayList<>();
    try (Connection connection = DatabaseManager.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
        while (resultSet.next()) {
            String items = resultSet.getArray("items") != null
                    ? String.join(", ", (String[]) resultSet.getArray("items").getArray())
                    : "No items"; // Обработка пустого массива

            gameStats.add(resultSet.getInt("game_stats_id") + ": " +
                    "( Player: " + resultSet.getString("player_nickname") +
                    ", Hero: " + resultSet.getString("hero_name") +
                    ", Items: [" + items + "]" +
                    ", Match Id: " + resultSet.getInt("match_id") +
                    ", Match Date: " + resultSet.getDate("match_date") +
                    ", Radiant Team: " + resultSet.getString("radiant_team") +
                    ", Dire Team: " + resultSet.getString("dire_team") +
                    ", Winner: " + resultSet.getString("winner") + ")"
            );
        }
    } catch (SQLException e) {
        System.out.println("Error while fetching game stats: " + e.getMessage());
    }
    return gameStats;
}

// Update
public void updateGameStats(int id, int gameId, int playerHeroId, int kills, int deaths, int assists, int lastHits, int goldPerMinute, int xpPerMinute, int netWorth, int finalLevel) {
    String query = "UPDATE GameStats SET game_id = ?, player_hero_id = ?, kills = ?, deaths = ?, assists = ?, last_hits = ?, gold_per_minute = ?, xp_per_minute = ?, net_worth = ?, final_level = ?  WHERE id = ?";
    try (Connection connection = DatabaseManager.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, gameId);
        statement.setInt(2, playerHeroId);
        statement.setInt(3, kills);
        statement.setInt(4, deaths);
        statement.setInt(5, assists);
        statement.setInt(6, lastHits);
        statement.setInt(7, goldPerMinute);
        statement.setInt(8, xpPerMinute);
        statement.setInt(9, netWorth);
        statement.setInt(10, finalLevel);
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