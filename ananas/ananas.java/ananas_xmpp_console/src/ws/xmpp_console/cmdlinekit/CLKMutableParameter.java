package ws.xmpp_console.cmdlinekit;

public interface CLKMutableParameter extends CLKParameter {

	void setValue(String value);

	void setDescription(String description);

	void setOption(boolean isOption);

}
