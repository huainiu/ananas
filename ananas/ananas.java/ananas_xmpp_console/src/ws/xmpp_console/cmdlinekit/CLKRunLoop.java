package ws.xmpp_console.cmdlinekit;

public interface CLKRunLoop {

	void run(CLKExecuteContext context);

	void exit();
}
