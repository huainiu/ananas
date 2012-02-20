package ws.xmpp_console.cmdlinekit;

public interface CLKCommandSet {

	void addCommand(CLKCommand cmd);

	void removeCommand(CLKCommand cmd);

	CLKCommand getCommand(String name);

	CLKCommand[] listCommands();

}
