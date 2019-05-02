package com.genius.database;

public class DaoFactory {

	public ArticleDao articleDao() {
		return new ArticleDao(getConnectionManager());
	}

	public CommentDao commentDao() {
		return new CommentDao(getConnectionManager());
	}

	private ConnectionManager getConnectionManager() {
		return new HomeConnectionManager();
	}
}
