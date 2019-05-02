package com.genius.database;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Connection")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseConnectionTest {

	public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	public static final String JDBC_URL = "jdbc:mariadb://localhost:3306/study";
	public static final String USER_NAME = "study";
	public static final String PASSWORD = "study";
	public static HikariDataSource DATASOURCE;
	public static final String INSERT_SQL = "INSERT INTO ARTICLE (GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE) VALUES (${grp}, ${ordinal}, ${level}, '${subject}', ${authorId}, ${status}, NOW());";
	public static final String PREPARED_INSERT_SQL = "INSERT INTO ARTICLE (GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE) VALUES (?, ?, ?, ?, ?, ?, NOW());";
	public static final String PREPARED_SELECT_SQL = "SELECT ID, GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE FROM ARTICLE;";
	public static final String PREPARED_SELECT_COUNT_SQL = "SELECT COUNT(*) CNT FROM ARTICLE;";

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
		Connection connection = getConnection();
		assertEquals("study", connection.getCatalog());
	}

	public static Connection getConnection() throws SQLException {
		DATASOURCE = new HikariDataSource();
		DATASOURCE.setDriverClassName(JDBC_DRIVER);
		DATASOURCE.setJdbcUrl(JDBC_URL);
		DATASOURCE.setUsername(USER_NAME);
		DATASOURCE.setPassword(PASSWORD);
		return DATASOURCE.getConnection();
	}

	public static void truncateArticle() {
		try (Connection connection = DATASOURCE.getConnection(); PreparedStatement statement = connection.prepareStatement(PREPARED_INSERT_SQL)) {
			statement.execute("TRUNCATE ARTICLE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}