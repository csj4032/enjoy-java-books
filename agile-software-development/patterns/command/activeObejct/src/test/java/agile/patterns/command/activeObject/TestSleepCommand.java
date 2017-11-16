package agile.patterns.command.activeObject;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import org.junit.Assert;
import org.junit.Test;

public class TestSleepCommand extends TestCase {

	public static void main(String[] args) {
		TestRunner.main(new String[]{"TestSleepCommand"});
	}

	private boolean commandExecuted = false;

	public TestSleepCommand(String name) {
		super(name);
	}

	@Test
	public void testSleep() throws Exception {
		Command wakeup = new Command() {
			public void execute() {
				commandExecuted = true;
			}
		};
		ActiveObjectEngine e = new ActiveObjectEngine();
		SleepCommand c = new SleepCommand(1000, e, wakeup);
		e.addCommand(c);
		long start = System.currentTimeMillis();
		e.run();
		long stop = System.currentTimeMillis();
		long sleepTime = (stop - start);
		Assert.assertFalse(sleepTime > 1000);
		Assert.assertTrue(sleepTime < 1100);
		Assert.assertTrue(commandExecuted);
		//assert("SleepTime " + sleepTime + " expected > 1000",sleepTime > 1000);
		//assert("SleepTime " + sleepTime + " expected < 1100",sleepTime < 1100);
		//assert("Command Executed",commandExecuted);
	}
}
