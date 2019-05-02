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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import static com.genius.database.DatabaseConnectionTest.DATASOURCE;
import static com.genius.database.DatabaseConnectionTest.PREPARED_SELECT_SQL;
import static com.genius.database.DatabaseConnectionTest.getConnection;

@Slf4j
@DisplayName("Select")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseSelectTest {

    private static final String PREPARED_INSERT_SQL = "INSERT INTO ARTICLE (GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE) VALUES (?, ?, ?, ?, ?, ?, NOW());";
    private static final int DUMMY_SIZE = 10000;

    @BeforeAll
    public static void setUp() throws SQLException {
        getConnection();
        dummyArticle();
    }

    @AfterEach
    public void init() {
        truncateArticle();
    }

    private static void truncateArticle() {
        try (Connection connection = DATASOURCE.getConnection(); PreparedStatement statement = connection.prepareStatement(PREPARED_INSERT_SQL)) {
            statement.execute("TRUNCATE ARTICLE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dummyArticle() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DATASOURCE.getConnection();
            statement = connection.prepareStatement(PREPARED_INSERT_SQL);
            for (int i = 1; i <= DUMMY_SIZE; i++) {
                Article article = Article.builder().grp(i).ordinal(1).level(1).subject("제목_" + i).authorId(i).status(1).build();
                statement.setInt(1, article.getGrp());
                statement.setInt(2, article.getOrdinal());
                statement.setInt(3, article.getLevel());
                statement.setString(4, article.getSubject());
                statement.setInt(5, article.getAuthorId());
                statement.setInt(6, article.getStatus());
                statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
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
            connection = DATASOURCE.getConnection();
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
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        Assertions.assertEquals(DUMMY_SIZE, articles.size());
    }
}