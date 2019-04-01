package agile.patterns.command.activeObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSleepCommand {

	private boolean commandExecuted = false;

	@Test
	public void testSleep() throws Exception {
		Command wakeup = () -> commandExecuted = true;
		ActiveObjectEngine e = new ActiveObjectEngine();
		SleepCommand c = new SleepCommand(1000, e, wakeup);
		e.addCommand(c);
		long start = System.currentTimeMillis();
		e.run();
		long stop = System.currentTimeMillis();
		long sleepTime = (stop - start);
		assertFalse(sleepTime > 1000);
		assertTrue(sleepTime < 1100);
		assertTrue(commandExecuted);
		//assert("SleepTime " + sleepTime + " expected > 1000",sleepTime > 1000);
		//assert("SleepTime " + sleepTime + " expected < 1100",sleepTime < 1100);
		//assert("Command Executed",commandExecuted);
	}
}
