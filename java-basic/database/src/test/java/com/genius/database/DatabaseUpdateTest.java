package com.genius.database;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.genius.database.DatabaseConnectionTest.*;
import static com.genius.database.DatabaseInsertTest.insertArticle;

@Slf4j
@DisplayName("Update")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseUpdateTest {

	@BeforeAll
	public static void setUp() throws SQLException {
		getConnection();
		truncateArticle();
		insertArticle(Article.builder().grp(1).ordinal(1).level(1).authorId(1).subject("").status(1).build());
	}

	@Test
	@Order(1)
	@DisplayName("UPDATE ARTICLE")
	public void updateArticle() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			connection = DATASOURCE.getConnection();
			preparedStatement = getPreparedStatement(connection);
			preparedStatement.setString(1, "제목");
			preparedStatement.setInt(2, 1);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		Assertions.assertEquals(1, result);
	}

	private PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(PREPARED_UPDATE_SQL);
		return preparedStatement;
	}


}