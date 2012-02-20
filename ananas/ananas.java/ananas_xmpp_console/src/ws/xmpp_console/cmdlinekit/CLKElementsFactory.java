package ws.xmpp_console.cmdlinekit;

public interface CLKElementsFactory {

	CLKCommandSet newCommandSet();

	CLKRunLoop newRunLoop();

	CLKParameterList newParameterList();
}
