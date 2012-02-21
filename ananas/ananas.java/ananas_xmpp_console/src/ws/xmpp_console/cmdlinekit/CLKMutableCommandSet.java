package ws.xmpp_console.cmdlinekit;

public interface CLKMutableCommandSet extends CLKCommandSet {

	void addCommand(CLKCommand cmd);

	void removeCommand(CLKCommand cmd);

}
