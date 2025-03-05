import java.sql.*;


public class DatabaseManager {
    // URL базы данных, имя пользователя и пароль
    private static final String URL = "jdbc:postgresql://localhost:5432/cybertracker";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}