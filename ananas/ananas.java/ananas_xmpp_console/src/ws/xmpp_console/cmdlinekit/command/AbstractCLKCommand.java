package ws.xmpp_console.cmdlinekit.command;

import ws.xmpp_console.cmdlinekit.CLKCommand;
import ws.xmpp_console.cmdlinekit.CLKElementsFactory;
import ws.xmpp_console.cmdlinekit.CLKMutableParameterList;
import ws.xmpp_console.cmdlinekit.CLKParameterList;
import ws.xmpp_console.cmdlinekit.DefaultCLKElementsFactory;

public abstract class AbstractCLKCommand implements CLKCommand {

	private final CLKMutableParameterList mParamList;

	public AbstractCLKCommand() {
		CLKElementsFactory factory = DefaultCLKElementsFactory.getFactory();
		this.mParamList = factory.newParameterList();
	}

	protected void registerParameter(String name, String defaultValue,
			boolean option, String description) {
		CLKElementsFactory factory = DefaultCLKElementsFactory.getFactory();
		this.mParamList.addParameter(factory.newParameter(name, defaultValue,
				option, description));
	}

	protected String getParameterValue(CLKParameterList plist, String name) {
		CLKParameterList mylist = this.getParameterList();
		// oparam ;
		// myparam ;
		return null;
	}

	@Override
	public CLKParameterList getParameterList() {
		return this.mParamList;
	}

	@Override
	public String getHelpTitle() {
		return "(no title)";
	}

	@Override
	public String getHelpContent() {
		return "(no content)";
	}

}
