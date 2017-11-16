package agile.patterns.command.activeObject;

public class SleepCommand implements Command {

	private long sleepTime = 0;
	private long startTime = 0;
	private final ActiveObjectEngine engine;
	private Command wakeupCommand = null;
	private boolean started = false;

	public SleepCommand(long milliseconds, ActiveObjectEngine e, Command wakeupCommand) {
		sleepTime = milliseconds;
		engine = e;
		this.wakeupCommand = wakeupCommand;
	}

	@Override
	public void execute() throws Exception {
		long currentTime = System.currentTimeMillis();
		if (!started) {
			started = true;
			startTime = currentTime;
			engine.addCommand(this);
		} else if ((currentTime - startTime) < sleepTime) {
			engine.addCommand(this);
		} else {
			engine.addCommand(wakeupCommand);
		}
	}
}
