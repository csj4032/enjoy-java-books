package com.genius.database;

import com.genius.database.datasource.CloseManager;
import com.genius.database.datasource.DataBaseManager;
import com.genius.database.datasource.HikariDataBaseManager;
import com.genius.database.domain.Article;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import static com.genius.database.DatabaseConnectionTest.*;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;
import static java.sql.Connection.TRANSACTION_REPEATABLE_READ;
import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

@Slf4j
@DisplayName("Isolation")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseIsolationTest {

    private static final String SUBJECT_0 = "제목";
    private static final String SUBJECT_1 = "제목1";
    private static final String SUBJECT_2 = "제목2";
    private static DataBaseManager connectionManager;
    private static CloseManager closeManager;
    private static Connection isolationConnection;

    @BeforeAll
    public static void setUp() throws SQLException {
        connectionManager = new HikariDataBaseManager();
        closeManager = new CloseManager();
        isolationConnection = connectionManager.getConnection();
        truncateArticle();
    }

    @Test
    @Order(1)
    @DisplayName("Dirty Reads : INSERT NOT COMMIT")
    public void dirtyReadsInsert() {
        Article article = Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build();
        PreparedStatement statement = null;
        try {
            isolationConnection.setAutoCommit(false);
            statement = isolationConnection.prepareStatement(PREPARED_INSERT_SQL);
            statement.setInt(1, article.getGrp());
            statement.setInt(2, article.getOrdinal());
            statement.setInt(3, article.getLevel());
            statement.setString(4, article.getSubject());
            statement.setInt(5, article.getAuthorId());
            statement.setInt(6, article.getStatus());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    @DisplayName("Dirty Reads: TRANSACTION_READ_UNCOMMITTED")
    public void transactionReadUncommitted() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        try {
            connection = connectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
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
        Assertions.assertEquals(1, articles.size());
    }

    @Test
    @Order(3)
    @DisplayName("Dirty Reads: TRANSACTION_READ_COMMITTED")
    public void transactionReadCommitted() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        try {
            connection = connectionManager.getConnection();
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(PREPARED_SELECT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            long id = resultSet.getLong("ID");
            int grp = resultSet.getInt("GRP");
            int ordinal = resultSet.getInt("ORDINAL");
            int level = resultSet.getInt("LEVEL");
            String subject = resultSet.getString("SUBJECT");
            int authorId = resultSet.getInt("AUTHOR_ID");
            int status = resultSet.getInt("STATUS");
            LocalDateTime regDate = resultSet.getTimestamp("REG_DATE").toLocalDateTime();
            articles.add(new Article(id, grp, ordinal, level, subject, authorId, status, regDate));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeManager.close(connection, preparedStatement, resultSet);
        }
        Assertions.assertEquals(0, articles.size());
    }

    @Test
    @Order(4)
    @DisplayName("Dirty Reads : CONNECTION_CLOSE")
    public void dirtyReadsClose() throws SQLException {
        isolationConnection.rollback();
        isolationConnection.close();
    }

    @Test
    @Order(5)
    @DisplayName("Non-Repeatable Reads : INSERT")
    public void nonRepeatableReadsInsert() throws SQLException {
        Article article = Article.builder().grp(1).ordinal(1).level(1).subject(SUBJECT_0).authorId(1).status(1).build();
        PreparedStatement statement = null;
        try {
            isolationConnection = connectionManager.getConnection();
            statement = isolationConnection.prepareStatement(PREPARED_INSERT_SQL);
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
            isolationConnection.close();
            statement.close();
        }
    }

    @Test
    @Order(6)
    @DisplayName("Non-Repeatable Reads: TRANSACTION_READ_COMMITTED 1")
    public void nonRepeatableReadsTransactionReadCommitted1() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        try {
            isolationConnection = connectionManager.getConnection();
            isolationConnection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            isolationConnection.setAutoCommit(false);
            preparedStatement = isolationConnection.prepareStatement(PREPARED_SELECT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            long id = resultSet.getLong("ID");
            int grp = resultSet.getInt("GRP");
            int ordinal = resultSet.getInt("ORDINAL");
            int level = resultSet.getInt("LEVEL");
            String subject = resultSet.getString("SUBJECT");
            int authorId = resultSet.getInt("AUTHOR_ID");
            int status = resultSet.getInt("STATUS");
            LocalDateTime regDate = resultSet.getTimestamp("REG_DATE").toLocalDateTime();
            articles.add(new Article(id, grp, ordinal, level, subject, authorId, status, regDate));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(SUBJECT_0, articles.get(0).getSubject());
    }

    @Test
    @Order(7)
    @DisplayName("Non-Repeatable Reads : UPDATE 1")
    public void nonRepeatableReadsUpdate1() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(PREPARED_UPDATE_SQL);
            preparedStatement.setString(1, SUBJECT_1);
            preparedStatement.setInt(2, 2);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.commit();
            closeManager.close(connection, preparedStatement);
        }
    }

    @Test
    @Order(8)
    @DisplayName("Non-Repeatable Reads: TRANSACTION_READ_COMMITTED 2")
    public void nonRepeatableReadsTransactionReadCommitted2() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        try {
            preparedStatement = isolationConnection.prepareStatement(PREPARED_SELECT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            long id = resultSet.getLong("ID");
            int grp = resultSet.getInt("GRP");
            int ordinal = resultSet.getInt("ORDINAL");
            int level = resultSet.getInt("LEVEL");
            String subject = resultSet.getString("SUBJECT");
            int authorId = resultSet.getInt("AUTHOR_ID");
            int status = resultSet.getInt("STATUS");
            LocalDateTime regDate = resultSet.getTimestamp("REG_DATE").toLocalDateTime();
            articles.add(new Article(id, grp, ordinal, level, subject, authorId, status, regDate));
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            isolationConnection.commit();
            closeManager.close(isolationConnection, preparedStatement, resultSet);
        }
        Assertions.assertEquals(SUBJECT_1, articles.get(0).getSubject());
    }

    @Test
    @Order(9)
    @DisplayName("Non-Repeatable Reads: TRANSACTION_REPEATABLE_READ 1")
    public void nonRepeatableReadsTransactionRepeatableRead1() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        try {
            isolationConnection = connectionManager.getConnection();
            isolationConnection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
            isolationConnection.setAutoCommit(false);
            preparedStatement = isolationConnection.prepareStatement(PREPARED_SELECT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            long id = resultSet.getLong("ID");
            int grp = resultSet.getInt("GRP");
            int ordinal = resultSet.getInt("ORDINAL");
            int level = resultSet.getInt("LEVEL");
            String subject = resultSet.getString("SUBJECT");
            int authorId = resultSet.getInt("AUTHOR_ID");
            int status = resultSet.getInt("STATUS");
            LocalDateTime regDate = resultSet.getTimestamp("REG_DATE").toLocalDateTime();
            articles.add(new Article(id, grp, ordinal, level, subject, authorId, status, regDate));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(SUBJECT_1, articles.get(0).getSubject());
    }

    @Test
    @Order(10)
    @DisplayName("Non-Repeatable Reads : UPDATE 2")
    public void nonRepeatableReadsUpdate2() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(PREPARED_UPDATE_SQL);
            statement.setString(1, SUBJECT_2);
            statement.setInt(2, 2);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            closeManager.close(connection, statement);
        }
    }

    @Test
    @Order(11)
    @DisplayName("Non-Repeatable Reads: TRANSACTION_REPEATABLE_READ 2")
    public void nonRepeatableReadsTransactionRepeatableRead2() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        try {
            preparedStatement = isolationConnection.prepareStatement(PREPARED_SELECT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            long id = resultSet.getLong("ID");
            int grp = resultSet.getInt("GRP");
            int ordinal = resultSet.getInt("ORDINAL");
            int level = resultSet.getInt("LEVEL");
            String subject = resultSet.getString("SUBJECT");
            int authorId = resultSet.getInt("AUTHOR_ID");
            int status = resultSet.getInt("STATUS");
            LocalDateTime regDate = resultSet.getTimestamp("REG_DATE").toLocalDateTime();
            articles.add(new Article(id, grp, ordinal, level, subject, authorId, status, regDate));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            isolationConnection.commit();
            isolationConnection.setAutoCommit(true);
            closeManager.close(isolationConnection, preparedStatement, resultSet);
        }
        Assertions.assertEquals(SUBJECT_1, articles.get(0).getSubject());
    }

    @Test
    @Order(12)
    @DisplayName("Phantom reads: TRANSACTION_REPEATABLE_READ 1")
    public void phantomReadsTransactionRepeatableRead1() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int cnt = 0;
        try {
            isolationConnection = connectionManager.getConnection();
            isolationConnection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
            isolationConnection.setAutoCommit(false);
            preparedStatement = isolationConnection.prepareStatement(PREPARED_SELECT_COUNT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            cnt = resultSet.getInt("CNT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(1, cnt);
    }

    @Test
    @Order(13)
    @DisplayName("Phantom reads: INSERT 1")
    public void phantomReadsInsert() throws SQLException {
        Article article = Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(PREPARED_INSERT_SQL);
            preparedStatement.setInt(1, article.getGrp());
            preparedStatement.setInt(2, article.getOrdinal());
            preparedStatement.setInt(3, article.getLevel());
            preparedStatement.setString(4, article.getSubject());
            preparedStatement.setInt(5, article.getAuthorId());
            preparedStatement.setInt(6, article.getStatus());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.commit();
            closeManager.close(connection, preparedStatement);
        }
    }

    @Test
    @Order(14)
    @DisplayName("Phantom reads: TRANSACTION_REPEATABLE_READ 2")
    public void phantomReadsTransactionRepeatableRead2() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int cnt = 0;
        try {
            preparedStatement = isolationConnection.prepareStatement(PREPARED_SELECT_COUNT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            cnt = resultSet.getInt("CNT");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            isolationConnection.setAutoCommit(true);
            isolationConnection.commit();
            closeManager.close(isolationConnection, preparedStatement, resultSet);
        }
        Assertions.assertEquals(1, cnt);
    }

    @Test
    @Order(15)
    @DisplayName("Phantom reads: TRANSACTION_SERIALIZABLE 1")
    public void phantomReadsTransactionSerializable1() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int cnt = 0;
        try {
            isolationConnection = connectionManager.getConnection();
            isolationConnection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            isolationConnection.setAutoCommit(false);
            preparedStatement = isolationConnection.prepareStatement(PREPARED_SELECT_COUNT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            cnt = resultSet.getInt("CNT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(2, cnt);
    }

    @Test
    @Order(16)
    @DisplayName("Phantom reads: INSERT 2")
    public void phantomReadsInsert2() throws SQLException {
        Article article = Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
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
            connection.commit();
            connection.close();
        }
    }

    @Test
    @Order(17)
    @DisplayName("Phantom reads: TRANSACTION_SERIALIZABLE 2")
    public void phantomReadsTransactionSerializable2() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int cnt = 0;
        try {
            preparedStatement = isolationConnection.prepareStatement(PREPARED_SELECT_COUNT_SQL);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            cnt = resultSet.getInt("CNT");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            isolationConnection.setAutoCommit(true);
            isolationConnection.commit();
            isolationConnection.close();
        }
        Assertions.assertEquals(3, cnt);
    }
}