package com.genius.database;

import com.genius.database.datasource.CloseManager;
import com.genius.database.datasource.DataBaseManager;
import com.genius.database.datasource.HikariDataBaseManager;
import com.genius.database.datasource.OfficeDataBaseManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Connection")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseConnectionTest {

    public static final String JDBC_URL = "jdbc:mariadb://localhost:3306/study";
    public static final String USER_NAME = "study";
    public static final String PASSWORD = "study";
    public static final String INSERT_SQL = "INSERT INTO ARTICLE (GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE) VALUES (${grp}, ${ordinal}, ${level}, '${subject}', ${authorId}, ${status}, NOW());";
    public static final String PREPARED_INSERT_SQL = "INSERT INTO ARTICLE (GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE) VALUES (?, ?, ?, ?, ?, ?, NOW());";
    public static final String PREPARED_SELECT_SQL = "SELECT ID, GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE FROM ARTICLE;";
    public static final String PREPARED_SELECT_COUNT_SQL = "SELECT COUNT(*) CNT FROM ARTICLE;";
    public static final String PREPARED_UPDATE_SQL = "UPDATE ARTICLE SET SUBJECT = ? WHERE ID = ?";
    public static final String PREPARED_DELETE_SQL = "DELETE ARTICLE WHERE ID = ?";
    public static final String PREPARED_TRUNCATE_SQL = "TRUNCATE ARTICLE";

    private DataBaseManager connectionManager = new HikariDataBaseManager();

    @Test
    @Order(1)
    @DisplayName("Database Connection")
    public void connectionTest() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
        assertTrue(connection.isValid(1000));
        assertEquals("study", connection.getCatalog());
        assertEquals(null, connection.getSchema());
    }

    @Test
    @Order(2)
    @DisplayName("Database DataBaseManager")
    public void connectionManagerTest() throws SQLException {
        OfficeDataBaseManager officeConnectionManager = new OfficeDataBaseManager();
        Connection connection = officeConnectionManager.getConnection();
        assertEquals("study", connection.getCatalog());
        assertEquals(null, connection.getSchema());
    }

    @Test
    @Order(3)
    @DisplayName("HikariCP JDBC Connection Pool")
    public void connectionPoolTest() throws SQLException {
        Connection connection = connectionManager.getConnection();
        assertEquals("study", connection.getCatalog());
    }

    public static void truncateArticle() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = new HikariDataBaseManager().getConnection();
            statement = connection.prepareStatement(PREPARED_TRUNCATE_SQL);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new CloseManager().close(connection, statement);
        }
    }
}