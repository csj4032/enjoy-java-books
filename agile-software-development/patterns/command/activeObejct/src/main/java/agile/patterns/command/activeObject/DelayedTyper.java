package agile.patterns.command.activeObject;

public class DelayedTyper implements Command {

	private static ActiveObjectEngine engine = new ActiveObjectEngine();
	private final long itsDelay;
	private final char itsChar;
	private static boolean stop = false;

	public DelayedTyper(long delay, char c) {
		itsDelay = delay;
		itsChar = c;
	}

	@Override
	public void execute() throws Exception {
		System.out.println(itsChar);
		if (!stop)
			delayAndRepeat();
	}

	private void delayAndRepeat() {
		engine.addCommand(new SleepCommand(2000, engine, this));
	}

	public static void main(String[] args) throws Exception {
		engine.addCommand(new DelayedTyper(100, '1'));
		engine.addCommand(new DelayedTyper(300, '3'));
		engine.addCommand(new DelayedTyper(500, '5'));
		engine.addCommand(new DelayedTyper(900, '9'));
		engine.addCommand(() -> stop = true);
		engine.run();
	}
}
