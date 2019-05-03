package com.genius.database.dao;

import com.genius.database.datasource.ConnectionManager;

public class UserDao {

	private ConnectionManager connectionManager;

	public UserDao(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
}
