package ws.xmpp_console.cmdlinekit;

public interface CLKCommandSet {

	CLKCommand getCommand(String name);

	CLKCommand[] listCommands();

}
