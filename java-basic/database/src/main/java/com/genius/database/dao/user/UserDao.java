package com.genius.database.dao.user;

import com.genius.database.domain.User;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import javax.sql.DataSource;

public class UserDao {

    private final DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UserDao(final DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public User findById(long id) {
        return jdbcTemplate.queryForObject("SELECT ID, NAME FROM USER WHERE ID = ?", User.class, id);
    }

    public List<User> findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT ID, NAME FROM USER WHERE NAME = ?", List.class, name);
    }

    public int insert(User user) {
        return jdbcTemplate.update("INSERT INTO USER (NAME, REG_DATE) VALUES (?, NOW())", user.getName());
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE USER SET NAME = ? WHERE ID = ?", user.getName(), user.getId());
    }
}