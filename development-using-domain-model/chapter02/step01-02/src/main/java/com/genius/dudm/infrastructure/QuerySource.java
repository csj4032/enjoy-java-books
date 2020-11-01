package com.genius.dudm.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuerySource {
	String sql;
	Object[] parameters;
	Connection connection;
	PreparedStatement preparedStatement;

	public QuerySource(String sql) {
		this.sql = sql;
	}

	public QuerySource(String sql, Object[] parameters) {
		this.sql = sql;
		this.parameters = parameters;
	}

	public int getParameterSize() {
		return parameters == null ? 0 : parameters.length;
	}

	public Object getParameter(int index) throws Exception {
		if (index >= getParameterSize()) throw new Exception("Index 값이 파라미터의 갯수보다 큽니다.");
		return parameters[index];
	}

	public PreparedStatement getPreparedStatement() throws SQLException {
		if (connection == null) connection = DatabaseManager.getConnection();
		if (preparedStatement == null) preparedStatement = connection.prepareStatement(sql);
		return preparedStatement;
	}

	public void close() {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {

			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {

			}
		}
	}
}
