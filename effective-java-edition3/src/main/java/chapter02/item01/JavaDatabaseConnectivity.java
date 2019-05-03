package chapter02.item01;

import java.sql.*;

public class JavaDatabaseConnectivity {

	public boolean isolationConnection() throws SQLException {
		// 서비스 제공자 프레임워크는 다양한 서비스 제공자들이 하나의 서비스를 구성하는 시스템
		// 1. 서비스 인터페이스 (java.sql.isolationConnection)
		// 4. 서비스 제공자 인터페이스 (Driver)
		Driver driver = new org.h2.Driver();
		// 2. 제공자 등록 API (구현체를 시스템에 등록하여 클라이언트가 쓸 수 있도록 함)
		DriverManager.registerDriver(driver);
		// 3. 서비스 접근 API (구현체에 대한 접근이 가능하도록 한다)
		// getConnection() '유연한 정적 팩터라'
		Connection connection = DriverManager.getConnection("jdbc:h2:~/effective;AUTO_SERVER=TRUE", null);
		Statement statement = connection.createStatement();
		return statement.execute("SELECT 1 FROM DUAL");
	}
}