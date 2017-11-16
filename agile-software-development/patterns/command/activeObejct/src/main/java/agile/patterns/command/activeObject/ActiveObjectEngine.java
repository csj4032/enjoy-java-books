package agile.patterns.command.activeObject;

import java.util.LinkedList;

public class ActiveObjectEngine {

	LinkedList<Command> itsCommands = new LinkedList();

	public void addCommand(Command c) {
		itsCommands.add(c);
	}

	public void run() throws Exception {
		while (!itsCommands.isEmpty()) {
			Command c = itsCommands.getFirst();
			itsCommands.removeFirst();
			c.execute();
		}
	}
}