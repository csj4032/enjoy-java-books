package com.genius.database;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("데이터베이스 접속")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseConnectionTest {

	public static final String JDBC_URL = "jdbc:mariadb://db.choibom.com:3306/study";
	public static final String USER_NAME = "study";
	public static final String PASSWORD = "study";

	@Test
	@DisplayName("데이터베이스 접속")
	public void connectionTest() throws SQLException {
		Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
		assertTrue(connection.isValid(1000));
		assertEquals("study", connection.getCatalog());
		assertEquals(null, connection.getSchema());
	}

	@Test
	@DisplayName("HikariCP JDBC Connection Pool")
	public void connectionPoolTest() throws SQLException {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setJdbcUrl(JDBC_URL);
		dataSource.setUsername(USER_NAME);
		dataSource.setPassword(PASSWORD);
		Connection connection = dataSource.getConnection();
		assertEquals("study", connection.getCatalog());
	}
}
