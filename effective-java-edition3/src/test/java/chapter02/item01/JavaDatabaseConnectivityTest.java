package chapter02.item01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class JavaDatabaseConnectivityTest {

	private JavaDatabaseConnectivity javaDatabaseConnectivity;

	@BeforeEach
	private void setUp() {
		javaDatabaseConnectivity = new JavaDatabaseConnectivity();
	}

	@Test
	public void connectionTest() throws SQLException {
		assertThat(javaDatabaseConnectivity.connection(), is(true));
	}
}