package com.genius.database.dao;

import com.genius.database.dao.article.ArticleDao;
import com.genius.database.dao.user.UserDao;
import com.genius.database.datasource.DataBaseManager;
import com.genius.database.datasource.HikariDataBaseManager;
import com.genius.database.datasource.HomeDataBaseManager;

import javax.sql.DataSource;

public class DaoFactory {

    public ArticleDao articleDao() {
        return new ArticleDao(getConnectionManager());
    }

    public UserDao userDao() {
        return new UserDao(getDataSource());
    }

    private DataBaseManager getConnectionManager() {
        return new HomeDataBaseManager();
    }

    private DataSource getDataSource() {
        return new HikariDataBaseManager().getDataSource();
    }
}
