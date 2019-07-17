package com.genius.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genius.database.datasource.CloseManager;
import com.genius.database.datasource.DataBaseManager;
import com.genius.database.datasource.HikariDataBaseManager;
import com.genius.database.domain.Article;

import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.*;
import java.util.Map;

import static com.genius.database.DatabaseConnectionTest.INSERT_SQL;
import static com.genius.database.DatabaseConnectionTest.PREPARED_INSERT_SQL;
import static com.genius.database.DatabaseConnectionTest.truncateArticle;

@DisplayName("Insert")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseInsertTest {

	private static DataBaseManager connectionManager;
	private static CloseManager closeManager;

	@BeforeAll
	public static void setUp() {
		connectionManager = new HikariDataBaseManager();
		closeManager = new CloseManager();
		truncateArticle();
	}

	@Test
	@Order(1)
	@DisplayName("Statement")
	public void statement() {
		StringSubstitutor stringSubstitutor = new StringSubstitutor(new ObjectMapper().convertValue(Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build(), Map.class));
		try (Connection connection = connectionManager.getConnection(); Statement statement = connection.createStatement()) {
			statement.executeUpdate(stringSubstitutor.replace(INSERT_SQL));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	@DisplayName("PrepareStatement")
	public void prepareStatement() {
		insertArticle(Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build());
	}

	public static void insertArticle(Article article) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connectionManager.getConnection();
			statement = connection.prepareStatement(PREPARED_INSERT_SQL);
			statement.setInt(1, article.getGrp());
			statement.setInt(2, article.getOrdinal());
			statement.setInt(3, article.getLevel());
			statement.setString(4, article.getSubject());
			statement.setInt(5, article.getAuthorId());
			statement.setInt(6, article.getStatus());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeManager.close(connection, statement);
		}
	}
}