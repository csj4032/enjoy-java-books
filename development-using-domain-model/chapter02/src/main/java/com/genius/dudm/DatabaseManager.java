package com.genius.dudm;

import java.sql.*;

public class DatabaseManager {

	private DatabaseManager() {

	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mariadb://localhost:3306/primavera?user=primavera&password=primavera");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
		if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
		if (connection != null) try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}
