package com.genius.dudm.domain;

import com.genius.dudm.infrastructure.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuerySource {

	private final String sql;
	private Connection connection;
	private Object[] parameters;
	private PreparedStatement preparedStatement;

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
		if (index >= getParameterSize()) throw new Exception("Index");
		return parameters[index];
	}

	public PreparedStatement getPrepareStatement() throws SQLException {
		if (connection == null) connection = DatabaseManager.getConnection();
		if (preparedStatement == null) preparedStatement = connection.prepareStatement(sql);
		return preparedStatement;
	}

	public void close() {
		if (preparedStatement != null) try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (connection != null) try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
