package com.genius.database.datasource.core;

import com.genius.database.datasource.CloseManager;
import com.genius.database.datasource.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeniusJdbcTemplate {

    private ConnectionManager connectionManager;

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public int template(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = statementStrategy.getPreparedStatement(connection);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new CloseManager().close(connection, preparedStatement);
        }
        return result;
    }

    public <T> T templateForObject(StatementStrategy statementStrategy, GeniusRowMapper<T> rowMapper) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = statementStrategy.getPreparedStatement(connection);
            resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet, resultSet.getFetchSize());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new CloseManager().close(connection, preparedStatement, resultSet);
        }
        return null;
    }
}
