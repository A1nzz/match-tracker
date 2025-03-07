package org.example.utils;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/cybertracker";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    private DBConnection() {

    }
    public static Connection getConnection() throws SQLException {

        Connection conn = null;
        try {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}