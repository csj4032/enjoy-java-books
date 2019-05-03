package com.genius.database.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OfficeConnectionManager implements ConnectionManager {

    public static final String JDBC_URL = "jdbc:mariadb://db.choibom.com:3306/study";
    public static final String USERNAME = "study";
    public static final String PASSWORD = "study";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}