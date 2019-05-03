package com.genius.database.dao.article;

import com.genius.database.datasource.ConnectionManager;
import com.genius.database.datasource.core.GeniusJdbcTemplate;
import com.genius.database.datasource.core.GeniusRowMapper;
import com.genius.database.datasource.core.StatementStrategy;
import com.genius.database.domain.Article;

import org.apache.commons.text.StringSubstitutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ArticleDao {
    private static final String TRUNCATE_SQL = "TRUNCATE ARTICLE";
    private static final String GET_SQL = "SELECT ID, GRP, ORDINAL, LEVEL, SUBJECT, AUTHOR_ID, STATUS, REG_DATE FROM ARTICLE WHERE ID = ${id}";
    private static final String COUNT_SQL = "SELECT COUNT(*) FROM ARTICLE";
    private static final String DELETE_SQL = "DELETE FROM ARTICLE";

    private GeniusJdbcTemplate jdbcTemplate;

    public ArticleDao(ConnectionManager connectionManager) {
        this.jdbcTemplate = new GeniusJdbcTemplate();
        jdbcTemplate.setConnectionManager(connectionManager);
    }

    public void setJdbcTemplate(GeniusJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void truncate() {
        executeSql(TRUNCATE_SQL);
    }

    public int add(final Article article) {
        return jdbcTemplate.template(new AddStatementStrategy(article));
    }

    public Article get(int id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return executeSql(GET_SQL, param, (resultSet, rowNum) -> {
            resultSet.first();
            return Article.builder().id(resultSet.getLong("ID")).subject(resultSet.getString("SUBJECT")).build();
        });
    }

    public void deleteAll() {
        class DeleteAllStatementStrategy implements StatementStrategy {
            @Override
            public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
                return connection.prepareStatement(DELETE_SQL);
            }
        }
        jdbcTemplate.template(new DeleteAllStatementStrategy());
    }

    public int getCount() {
        return executeSql(COUNT_SQL, null, ((resultSet, rowNum) -> {
            resultSet.first();
            return resultSet.getInt(1);
        }));
    }

    private void executeSql(String query) {
        jdbcTemplate.template((connection) -> connection.prepareStatement(query));
    }

    private <T> T executeSql(String query, Map<String, Object> param, GeniusRowMapper<T> rowMapper) {
        return jdbcTemplate.templateForObject((connection) -> connection.prepareStatement(StringSubstitutor.replace(query, param)), rowMapper);
    }
}
