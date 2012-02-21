package ws.xmpp_console.cmdlinekit;

public interface CLKParameter {

	String getName();

	String getValue();

	String getDescription();

	boolean isOption();
}
