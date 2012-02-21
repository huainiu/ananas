package ws.xmpp_console;

import ws.xmpp_console.cmdlinekit.CLKElementsFactory;
import ws.xmpp_console.cmdlinekit.CLKExecuteContext;
import ws.xmpp_console.cmdlinekit.CLKMutableCommandSet;
import ws.xmpp_console.cmdlinekit.CLKParameterList;
import ws.xmpp_console.cmdlinekit.CLKRunLoop;
import ws.xmpp_console.cmdlinekit.DefaultCLKElementsFactory;
import ws.xmpp_console.cmdlinekit.DefaultCLKExecuteContext;
import ws.xmpp_console.cmdlinekit.command.AbstractCLKCommand;

public class Main {

	public static void main(String[] arg) {

		System.out.println("ananas xmpp console");
		CLKElementsFactory factory = DefaultCLKElementsFactory.getFactory();
		CLKMutableCommandSet cmdset = factory.newCommandSet();
		CLKRunLoop runloop = factory.newRunLoop();
		DefaultCLKExecuteContext context = new DefaultCLKExecuteContext();
		context.mCommandSet = cmdset;
		cmdset.addCommand(new MySampleCommand());
		runloop.run(context);

	}

	private static class MySampleCommand extends AbstractCLKCommand {

		public MySampleCommand() {
			this.registerParameter("aaa", "def-val", true, "desc a");
			this.registerParameter("bbb", null, false, "desc b");
		}

		@Override
		public String getName() {
			return "sample";
		}

		@Override
		public void execute(CLKExecuteContext context) {

			CLKParameterList plist = context.getParameterList();
			String aaa = this.getParameterValue(plist, "aaa");
			String bbb = this.getParameterValue(plist, "bbb");

			throw new RuntimeException(this + " aaa:" + aaa + " bbb:" + bbb);

		}
	}

}
