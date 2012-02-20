package ws.xmpp_console.cmdlinekit;

public interface CLKCommand {

	String getName();

	String getHelpTitle();

	String getHelpContent();

	void execute(CLKExecuteContext context);

	CLKParameterList getParameterList();
}
