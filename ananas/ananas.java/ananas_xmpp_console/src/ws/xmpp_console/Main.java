package ws.xmpp_console;

import ws.xmpp_console.cmdlinekit.CLKCommandSet;
import ws.xmpp_console.cmdlinekit.CLKElementsFactory;
import ws.xmpp_console.cmdlinekit.CLKRunLoop;
import ws.xmpp_console.cmdlinekit.DefaultCLKElementsFactory;
import ws.xmpp_console.cmdlinekit.DefaultCLKExecuteContext;

public class Main {

	public static void main(String[] arg) {

		System.out.println("ws xmpp console");

		CLKElementsFactory factory = DefaultCLKElementsFactory.getFactory();
		CLKCommandSet cmdset = factory.newCommandSet();
		CLKRunLoop runloop = factory.newRunLoop();
		DefaultCLKExecuteContext context = new DefaultCLKExecuteContext();
		runloop.run(context);

	}
}
