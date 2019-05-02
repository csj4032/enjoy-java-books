package com.genius.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static com.genius.database.DatabaseConnectionTest.DATASOURCE;
import static com.genius.database.DatabaseConnectionTest.INSERT_SQL;
import static com.genius.database.DatabaseConnectionTest.JDBC_DRIVER;
import static com.genius.database.DatabaseConnectionTest.JDBC_URL;
import static com.genius.database.DatabaseConnectionTest.PASSWORD;
import static com.genius.database.DatabaseConnectionTest.PREPARED_INSERT_SQL;
import static com.genius.database.DatabaseConnectionTest.USER_NAME;
import static com.genius.database.DatabaseConnectionTest.getConnection;
import static com.genius.database.DatabaseConnectionTest.truncateArticle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Insert")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseInsertTest {

	@BeforeAll
	public static void setUp() throws SQLException {
		getConnection();
	}

	@AfterAll
	public static void after() {
		truncateArticle();
	}

	@Test
	@Order(1)
	@DisplayName("Statement")
	public void statement() {
		StringSubstitutor stringSubstitutor = new StringSubstitutor(new ObjectMapper().convertValue(Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build(), Map.class));
		try (Connection connection = DATASOURCE.getConnection(); Statement statement = connection.createStatement()) {
			statement.executeUpdate(stringSubstitutor.replace(INSERT_SQL));
			statement.executeUpdate(stringSubstitutor.replace(INSERT_SQL));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	@DisplayName("PrepareStatement")
	public void prepareStatement() throws SQLException {
		insertArticle(Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build());
	}

	public static void insertArticle(Article article) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DATASOURCE.getConnection();
			statement = connection.prepareStatement(PREPARED_INSERT_SQL);
			statement.setInt(1, article.getGrp());
			statement.setInt(2, article.getOrdinal());
			statement.setInt(3, article.getLevel());
			statement.setString(4, article.getSubject());
			statement.setInt(5, article.getAuthorId());
			statement.setInt(6, article.getStatus());
			statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			statement.close();
			connection.close();
		}
	}
}