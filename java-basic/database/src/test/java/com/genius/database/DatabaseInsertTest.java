package com.genius.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genius.database.datasource.CloseManager;
import com.genius.database.datasource.ConnectionManager;
import com.genius.database.datasource.HikariConnectionManager;
import com.genius.database.domain.Article;

import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static com.genius.database.DatabaseConnectionTest.INSERT_SQL;
import static com.genius.database.DatabaseConnectionTest.PREPARED_INSERT_SQL;

@DisplayName("Insert")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseInsertTest {

    private static ConnectionManager connectionManager;
    private static CloseManager closeManager;

    @BeforeAll
    public static void setUp() {
        connectionManager = new HikariConnectionManager();
        closeManager = new CloseManager();
    }

    @AfterAll
    public static void after() {
    }

    @Test
    @Order(1)
    @DisplayName("Statement")
    public void statement() {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(new ObjectMapper().convertValue(Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build(), Map.class));
        try (Connection connection = connectionManager.getConnection(); Statement statement = connection.createStatement()) {
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
            connection = connectionManager.getConnection();
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
            closeManager.close(connection, statement);
        }
    }
}