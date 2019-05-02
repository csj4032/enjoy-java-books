package com.genius.database;

public class ArticleDao {

	private JdbcTemplate jdbcTemplate;

	public ArticleDao(ConnectionManager connectionManager) {
		this.jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setConnectionManager(connectionManager);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void truncate() {
		executeSql("TRUNCATE ARTICLE");
	}

	public int add(final Article article) {
		return jdbcTemplate.template(new AddStatementStrategy(article));
	}

	public Article get(int id) {
		return executeSql("SELECT ID, GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE FROM ARTICLE WHERE ID = ?", new Object[]{id}, (resultSet, rowNum) -> {
			resultSet.next();
			Article article = new Article();
			article.setId(resultSet.getLong("ID"));
			article.setSubject(resultSet.getString("SUBJECT"));
			return article;
		});
	}

	public void deleteAll() {
		jdbcTemplate.template(new DeleteAllStatementStrategy());
	}

	public int getCount() {
		return executeSql("SELECT COUNT(*) FROM ARTICLE", null, ((resultSet, rowNum) -> {
			resultSet.first();
			return resultSet.getInt(1);
		}));
	}

	private void executeSql(String query) {
		jdbcTemplate.template((connection) -> connection.prepareStatement(query));
	}

	private <T> T executeSql(String query, Object[] param, RowMapper<T> rowMapper) {
		return jdbcTemplate.templateForObject((connection) -> connection.prepareStatement(query), param, rowMapper);
	}

}
