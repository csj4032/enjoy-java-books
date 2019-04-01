package chapter10.item70;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedExceptionTest {

	@Test
	public void checkedTest() {
		var value = 12;
		var checkedException = new CheckedException();
		try {
			checkedException.checked(value);
		} catch (Exception e) {
			value = 10;
		}
		log.info("{}", value);
	}
}