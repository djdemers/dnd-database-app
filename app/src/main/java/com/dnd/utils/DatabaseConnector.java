package com.dnd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static String url;
    private static String username;
    private static String password;

    public static void setCredentials(String inputUrl, String inputUsername, String inputPassword) {
        url = inputUrl;
        username = inputUsername;
        password = inputPassword;
    }

    public static Connection getConnection() throws SQLException {
        if (url == null || username == null || password == null) {
            throw new IllegalStateException("Database credentials are not set.");
        }
        return DriverManager.getConnection(url, username, password);
    }
}

