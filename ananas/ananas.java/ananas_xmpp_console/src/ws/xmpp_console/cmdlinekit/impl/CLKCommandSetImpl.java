package ws.xmpp_console.cmdlinekit.impl;

import java.util.Hashtable;

import ws.xmpp_console.cmdlinekit.CLKCommand;
import ws.xmpp_console.cmdlinekit.CLKCommandSet;
import ws.xmpp_console.cmdlinekit.CLKMutableCommandSet;
import ws.xmpp_console.cmdlinekit.command.DefaultExitCommand;
import ws.xmpp_console.cmdlinekit.command.DefaultHelpCommand;
import ws.xmpp_console.cmdlinekit.command.DefaultTestCommand;

class CLKCommandSetImpl implements CLKCommandSet, CLKMutableCommandSet {

	private final Hashtable<String, CLKCommand> mTable;

	public CLKCommandSetImpl() {
		this.mTable = new Hashtable<String, CLKCommand>();

		this.addCommand(new DefaultHelpCommand());
		this.addCommand(new DefaultExitCommand());
		this.addCommand(new DefaultTestCommand());
	}

	@Override
	public void addCommand(CLKCommand cmd) {
		if (cmd != null) {
			String name = cmd.getName();
			if (name != null) {
				this.mTable.put(name, cmd);
			}
		}
	}

	@Override
	public void removeCommand(CLKCommand cmd) {
		if (cmd != null) {
			String name = cmd.getName();
			if (name != null) {
				this.mTable.remove(name);
			}
		}
	}

	@Override
	public CLKCommand getCommand(String name) {
		return this.mTable.get(name);
	}

	@Override
	public CLKCommand[] listCommands() {
		return this.mTable.values().toArray(new CLKCommand[0]);
	}

}
