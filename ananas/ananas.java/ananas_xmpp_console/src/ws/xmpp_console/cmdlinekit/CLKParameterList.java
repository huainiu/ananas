package ws.xmpp_console.cmdlinekit;

public interface CLKParameterList {

	CLKParameter[] listParameters();

	CLKParameter getParameter(String name);

	void addParameter(CLKParameter param);

}
