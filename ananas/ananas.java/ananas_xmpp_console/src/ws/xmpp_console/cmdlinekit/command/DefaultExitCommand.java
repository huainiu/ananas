package ws.xmpp_console.cmdlinekit.command;

import ws.xmpp_console.cmdlinekit.CLKCommand;
import ws.xmpp_console.cmdlinekit.CLKExecuteContext;
import ws.xmpp_console.cmdlinekit.CLKParameterList;

public class DefaultExitCommand implements CLKCommand {

	@Override
	public String getName() {
		return "exit";
	}

	@Override
	public String getHelpTitle() {
		return "title of " + this.getName();
	}

	@Override
	public String getHelpContent() {
		return "content of " + this.getName();
	}

	@Override
	public void execute(CLKExecuteContext context) {
		context.getRunLoop().exit();
	}

	@Override
	public CLKParameterList getParameterList() {
		return null;
	}

}
