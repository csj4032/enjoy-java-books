package com.genius.database;

import com.genius.database.datasource.CloseManager;
import com.genius.database.datasource.ConnectionManager;
import com.genius.database.datasource.HikariConnectionManager;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import static com.genius.database.DatabaseConnectionTest.*;

@Slf4j
@DisplayName("Select")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseSelectTest {

    private static final int DUMMY_SIZE = 10000;
    private static ConnectionManager connectionManager;
    private static CloseManager closeManager;

    @BeforeAll
    public static void setUp() throws SQLException {
        connectionManager = new HikariConnectionManager();
        closeManager = new CloseManager();
        truncateArticle();
        dummyArticle();
    }

    @Test
    @Order(1)
    @DisplayName("SELECT ARTICLE")
    public void selectArticle() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(PREPARED_SELECT_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                int grp = resultSet.getInt("GRP");
                int ordinal = resultSet.getInt("ORDINAL");
                int level = resultSet.getInt("LEVEL");
                String subject = resultSet.getString("SUBJECT");
                int authorId = resultSet.getInt("AUTHOR_ID");
                int status = resultSet.getInt("STATUS");
                LocalDateTime regDate = resultSet.getTimestamp("REG_DATE").toLocalDateTime();
                articles.add(new Article(id, grp, ordinal, level, subject, authorId, status, regDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeManager.close(connection, preparedStatement, resultSet);
        }
        Assertions.assertEquals(DUMMY_SIZE, articles.size());
    }

    private static void dummyArticle() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(PREPARED_INSERT_SQL);
            for (int i = 1; i <= DUMMY_SIZE; i++) {
                Article article = Article.builder().grp(i).ordinal(1).level(1).subject("제목_" + i).authorId(i).status(1).build();
                preparedStatement.setInt(1, article.getGrp());
                preparedStatement.setInt(2, article.getOrdinal());
                preparedStatement.setInt(3, article.getLevel());
                preparedStatement.setString(4, article.getSubject());
                preparedStatement.setInt(5, article.getAuthorId());
                preparedStatement.setInt(6, article.getStatus());
                preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeManager.close(connection, preparedStatement);
        }
    }
}