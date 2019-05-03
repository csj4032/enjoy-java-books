package com.genius.database.datasource;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnectionManager implements ConnectionManager {

    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://db.choibom.com:3306/study";
    private static final String USER_NAME = "study";
    private static final String PASSWORD = "study";

    @Override
    public Connection getConnection() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(JDBC_DRIVER);
        hikariDataSource.setJdbcUrl(JDBC_URL);
        hikariDataSource.setUsername(USER_NAME);
        hikariDataSource.setPassword(PASSWORD);
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
