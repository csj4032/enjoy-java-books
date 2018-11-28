package chapter02.item01;

import java.sql.*;

public class JavaDatabaseConnectivity {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// JDBC > 서비스 제공자 프레임워크는 다양한 서비스 제공자들이 하나의 서비스를 구성하는 시스템
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3601", null);
		Statement statement = connection.createStatement();
		statement.execute("");
	}
}