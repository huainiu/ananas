package ws.xmpp_console.cmdlinekit.command;

import ws.xmpp_console.cmdlinekit.CLKExecuteContext;
import ws.xmpp_console.cmdlinekit.CLKParameterList;

public class TheSampleCommand  extends AbstractCLKCommand {

	public TheSampleCommand() {
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
