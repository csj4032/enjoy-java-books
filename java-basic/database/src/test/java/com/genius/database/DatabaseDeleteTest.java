package com.genius.database;

import com.genius.database.datasource.DataBaseManager;
import com.genius.database.datasource.HikariDataBaseManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import static com.genius.database.DatabaseConnectionTest.PREPARED_DELETE_SQL;

@Slf4j
@DisplayName("Delete")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseDeleteTest {

    private static DataBaseManager connectionManager;

    @BeforeAll
    public static void setUp() {
        connectionManager = new HikariDataBaseManager();
    }

    @Test
    @Order(1)
    @DisplayName("DELETE ARTICLE")
    public void deleteArticle() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = getPreparedStatement(connection);
            preparedStatement.setInt(1, 1);
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
        preparedStatement = connection.prepareStatement(PREPARED_DELETE_SQL);
        return preparedStatement;
    }
}