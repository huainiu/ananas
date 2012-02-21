package ws.xmpp_console.cmdlinekit;

public interface CLKInputDialog {

	String readInput(CLKExecuteContext context, String prompt, String ending);

}
