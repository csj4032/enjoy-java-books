package com.genius.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("데이터베이스 접속")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseInsertTest {

	private static final String JDBC_URL = "jdbc:mariadb://db.choibom.com:3306/study";
	private static final String USER_NAME = "study";
	private static final String PASSWORD = "study";
	private static final HikariDataSource dataSource = new HikariDataSource();
	private static Connection connection;
	private static final String INSERT_SQL = "INSERT INTO ARTICLE (GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE) VALUES (${grp}, ${ordinal}, ${level}, '${subject}', ${authorId}, ${status}, NOW());";

	@BeforeAll
	public static void setUp() throws SQLException {
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setJdbcUrl(JDBC_URL);
		dataSource.setUsername(USER_NAME);
		dataSource.setPassword(PASSWORD);
		connection = dataSource.getConnection();

	}

	@Test
	@Order(1)
	@DisplayName("Statement")
	public void statement() throws SQLException {
		StringSubstitutor stringSubstitutor = new StringSubstitutor(new ObjectMapper().convertValue(Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build(), Map.class));
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
			statement.executeUpdate(stringSubstitutor.replace(INSERT_SQL));
			statement.executeUpdate(stringSubstitutor.replace(INSERT_SQL));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	@DisplayName("Commit Rollback")
	public void commitRollback() throws SQLException {
		StringSubstitutor stringSubstitutor = new StringSubstitutor(new ObjectMapper().convertValue(Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build(), Map.class));
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
			connection.setAutoCommit(false);
			statement.executeUpdate(stringSubstitutor.replace(INSERT_SQL));
			statement.executeUpdate(stringSubstitutor.replace(INSERT_SQL));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.rollback();
			connection.setAutoCommit(true);
		}
	}
}
