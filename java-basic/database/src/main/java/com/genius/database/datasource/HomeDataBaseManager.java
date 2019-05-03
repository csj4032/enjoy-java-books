package com.genius.database.datasource;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class HomeDataBaseManager implements DataBaseManager {

	public static final String JDBC_URL = "jdbc:mariadb://db.choibom.com:3306/study";
	public static final String USER_NAME = "study";
	public static final String PASSWORD = "study";

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DataSource getDataSource() throws SQLException {
		MariaDbDataSource dataSource = new MariaDbDataSource(JDBC_URL);
		dataSource.setUserName(USER_NAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}
}
