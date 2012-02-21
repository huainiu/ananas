package ws.xmpp_console.cmdlinekit.command;

import java.io.PrintStream;

import ws.xmpp_console.cmdlinekit.CLKElementsFactory;
import ws.xmpp_console.cmdlinekit.CLKExecuteContext;
import ws.xmpp_console.cmdlinekit.CLKMutableCommandSet;
import ws.xmpp_console.cmdlinekit.CLKRunLoop;
import ws.xmpp_console.cmdlinekit.DefaultCLKElementsFactory;
import ws.xmpp_console.cmdlinekit.DefaultCLKExecuteContext;
import ws.xmpp_console.cmdlinekit.DefaultCLKPrompt;

public class TheDevToolsCommand extends AbstractCLKCommand {

	public TheDevToolsCommand() {
	}

	@Override
	public String getName() {
		return "dev-tools";
	}

	@Override
	public void execute(CLKExecuteContext context) {

		final PrintStream ps = context.getPrint();
		ps.println("ACLCDT - ananas command line console develop tools");
		CLKElementsFactory factory = DefaultCLKElementsFactory.getFactory();
		CLKMutableCommandSet cmdset = factory.newCommandSet();
		CLKRunLoop runloop = factory.newRunLoop();
		DefaultCLKExecuteContext context2 = new DefaultCLKExecuteContext();
		context2.mCommandSet = cmdset;
		context2.mPrompt = new DefaultCLKPrompt("[ACLCDT]:");
		cmdset.addCommand(new TheSampleCommand());
		cmdset.addCommand(new TheTestCommand());
		runloop.run(context2);

	}
}
