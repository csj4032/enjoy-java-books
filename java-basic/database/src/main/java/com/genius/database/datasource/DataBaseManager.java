package com.genius.database.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public interface DataBaseManager {

	Connection getConnection() throws SQLException;

	DataSource getDataSource() throws SQLException;
}
