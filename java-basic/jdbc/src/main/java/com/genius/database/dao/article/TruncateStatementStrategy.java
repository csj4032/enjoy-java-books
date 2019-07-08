package com.genius.database.dao.article;

import com.genius.database.datasource.core.StatementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TruncateStatementStrategy implements StatementStrategy {

	@Override
	public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
		return connection.prepareStatement("TRUNCATE ARTICLE");
	}
}
