package com.genius.database;

import com.genius.database.datasource.CloseManager;
import com.genius.database.datasource.DataBaseManager;
import com.genius.database.datasource.HikariDataBaseManager;
import com.genius.database.domain.Article;

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

import static com.genius.database.DatabaseConnectionTest.PREPARED_INSERT_SQL;
import static com.genius.database.DatabaseConnectionTest.truncateArticle;

@DisplayName("Rollback")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseRollbackTest {

    private static DataBaseManager connectionManager;
    private static CloseManager closeManager;
    private static final String PREPARED_SELECT_SQL = "SELECT COUNT(*) AS CNT FROM ARTICLE";

    @BeforeAll
    public static void setUp() {
        connectionManager = new HikariDataBaseManager();
        closeManager = new CloseManager();
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
            connection = connectionManager.getConnection();
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
            closeManager.close(connection, statement);
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
            connection = connectionManager.getConnection();
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
            connection.setAutoCommit(true);
            closeManager.close(connection, statement);
        }
        int cnt = getCount();
        Assertions.assertEquals(0, cnt);
    }

    private int getCount() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(PREPARED_SELECT_SQL);
            resultSet = statement.executeQuery();
            resultSet.first();
            return resultSet.getInt("CNT");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeManager.close(connection, statement, resultSet);
        }
        return 0;
    }
}