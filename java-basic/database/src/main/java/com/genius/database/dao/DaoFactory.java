package com.genius.database.dao;

import com.genius.database.dao.article.ArticleDao;
import com.genius.database.datasource.ConnectionManager;
import com.genius.database.datasource.HomeConnectionManager;

public class DaoFactory {

	public ArticleDao articleDao() {
		return new ArticleDao(getConnectionManager());
	}

	public UserDao commentDao() {
		return new UserDao(getConnectionManager());
	}

	private ConnectionManager getConnectionManager() {
		return new HomeConnectionManager();
	}
}
