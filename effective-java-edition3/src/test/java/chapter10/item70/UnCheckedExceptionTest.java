package chapter10.item70;

import org.junit.jupiter.api.Test;

public class UnCheckedExceptionTest {

	@Test
	public void checkedTest() {
		var unCheckedException = new UnCheckedException();
		unCheckedException.unChecked(null);
	}
}