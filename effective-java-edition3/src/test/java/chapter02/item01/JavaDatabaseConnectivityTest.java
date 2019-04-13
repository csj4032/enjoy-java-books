package chapter02.item01;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class JavaDatabaseConnectivityTest {

	private static JavaDatabaseConnectivity javaDatabaseConnectivity;

	@BeforeAll
	public static void setUp() {
		javaDatabaseConnectivity = new JavaDatabaseConnectivity();
	}

	@Test
	@DisplayName("데이터베이스 커넥션 테스트")
	public void connectionTest() throws SQLException {
		assertThat(javaDatabaseConnectivity.connection(), is(true));
	}
}