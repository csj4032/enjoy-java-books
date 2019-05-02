package com.genius.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

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
			close(connection, preparedStatement);
		}
		return result;
	}

	public <T> T templateForObject(StatementStrategy statementStrategy, Object[] param, RowMapper<T> rowMapper) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			preparedStatement = statementStrategy.getPreparedStatement(connection);
			if (param != null) {
				preparedStatement.setInt(1, (int) param[0]);
			}
			resultSet = preparedStatement.executeQuery();
			return rowMapper.mapRow(resultSet, resultSet.getFetchSize());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, preparedStatement);
		}
		return null;
	}

	private void close(Connection connection, PreparedStatement preparedStatement) {
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
}
