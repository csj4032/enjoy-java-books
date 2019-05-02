package com.genius.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatementStrategy implements StatementStrategy {

	private Article article;

	public AddStatementStrategy(Article article) {
		this.article = article;
	}

	@Override
	public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ARTICLE (GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE) VALUES (?, ?, ?, ?, ?, ?, NOW())");
		preparedStatement.setInt(1, article.getGrp());
		preparedStatement.setInt(2, article.getOrdinal());
		preparedStatement.setInt(3, article.getLevel());
		preparedStatement.setString(4, article.getSubject());
		preparedStatement.setInt(5, article.getAuthorId());
		preparedStatement.setInt(6, article.getStatus());
		return preparedStatement;
	}
}
