package chapter06.item35;

import chapter06.item35.log.LogTarget;
import org.junit.Test;

public class LogAspectTest {

	@Test
	public void testMethod() throws Exception {
		LogTarget target = new LogTarget();
		target.logTargetMethod();
		target.logTargetMethod2();
	}
}