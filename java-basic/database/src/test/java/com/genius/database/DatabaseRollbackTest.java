package com.genius.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import static com.genius.database.DatabaseConnectionTest.DATASOURCE;
import static com.genius.database.DatabaseConnectionTest.PREPARED_INSERT_SQL;
import static com.genius.database.DatabaseConnectionTest.getConnection;
import static com.genius.database.DatabaseConnectionTest.truncateArticle;

@DisplayName("Rollback")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseRollbackTest {

    private static final String PREPARED_SELECT_SQL = "SELECT COUNT(*) AS CNT FROM ARTICLE";

    @BeforeAll
    public static void setUp() throws SQLException {
        getConnection();
        truncateArticle();
    }

    @AfterEach
    public void init() {
        truncateArticle();
    }

    @Test
    @Order(1)
    @DisplayName("NON ROLLBACK")
    public void nonRollback() throws SQLException {
        int index = new Random().nextInt(10);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DATASOURCE.getConnection();
            statement = connection.prepareStatement(PREPARED_INSERT_SQL);
            for (int i = 1; i <= 10; i++) {
                Article article = Article.builder().grp(i).ordinal(1).level(1).subject("제목_" + i).authorId(i).status(1).build();
                statement.setInt(1, article.getGrp());
                statement.setInt(2, article.getOrdinal());
                statement.setInt(3, article.getLevel());
                statement.setString(4, article.getSubject());
                statement.setInt(5, article.getAuthorId());
                statement.setInt(6, article.getStatus());
                statement.executeQuery();
                if (i == index) {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
        int cnt = getCount();
        Assertions.assertEquals(index, cnt);
    }

    @Test
    @Order(2)
    @DisplayName("ROLLBACK")
    public void rollback() throws SQLException {
        int index = new Random().nextInt(10);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DATASOURCE.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(PREPARED_INSERT_SQL);
            for (int i = 1; i <= 10; i++) {
                Article article = Article.builder().grp(i).ordinal(1).level(1).subject("제목_" + i).authorId(i).status(1).build();
                statement.setInt(1, article.getGrp());
                statement.setInt(2, article.getOrdinal());
                statement.setInt(3, article.getLevel());
                statement.setString(4, article.getSubject());
                statement.setInt(5, article.getAuthorId());
                statement.setInt(6, article.getStatus());
                statement.executeQuery();
                if (i == index) {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
            if (statement != null) statement.close();
        }
        int cnt = getCount();
        Assertions.assertEquals(0, cnt);
    }

    private int getCount() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DATASOURCE.getConnection();
            statement = connection.prepareStatement(PREPARED_SELECT_SQL);
            resultSet = statement.executeQuery();
            resultSet.first();
            return resultSet.getInt("CNT");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
            if (resultSet != null) resultSet.close();
        }
        return 0;
    }
}