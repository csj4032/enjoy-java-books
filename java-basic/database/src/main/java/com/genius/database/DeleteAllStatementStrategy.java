package com.genius.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatementStrategy implements StatementStrategy {
	@Override
	public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
		return connection.prepareStatement("DELETE FROM ARTICLE");
	}
}
