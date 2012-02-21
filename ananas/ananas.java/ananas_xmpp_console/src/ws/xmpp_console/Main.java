package ws.xmpp_console;

import ws.xmpp_console.cmdlinekit.CLKElementsFactory;
import ws.xmpp_console.cmdlinekit.CLKMutableCommandSet;
import ws.xmpp_console.cmdlinekit.CLKRunLoop;
import ws.xmpp_console.cmdlinekit.DefaultCLKElementsFactory;
import ws.xmpp_console.cmdlinekit.DefaultCLKExecuteContext;
import ws.xmpp_console.cmdlinekit.command.TheDevToolsCommand;

public class Main {

	public static void main(String[] arg) {

		System.out.println("ananas xmpp console");
		CLKElementsFactory factory = DefaultCLKElementsFactory.getFactory();
		CLKMutableCommandSet cmdset = factory.newCommandSet();
		CLKRunLoop runloop = factory.newRunLoop();
		DefaultCLKExecuteContext context = new DefaultCLKExecuteContext();
		context.mCommandSet = cmdset;
		cmdset.addCommand(new TheDevToolsCommand());
		runloop.run(context);

	}

}
