package ws.xmpp_console.cmdlinekit.impl;

import ws.xmpp_console.cmdlinekit.CLKCommandSet;
import ws.xmpp_console.cmdlinekit.CLKElementsFactory;
import ws.xmpp_console.cmdlinekit.CLKParameterList;
import ws.xmpp_console.cmdlinekit.CLKRunLoop;

public class CLKElementsFactoryImpl implements CLKElementsFactory {

	@Override
	public CLKCommandSet newCommandSet() {
		return new CLKCommandSetImpl();
	}

	@Override
	public CLKRunLoop newRunLoop() {
		return new CLKRunLoopImpl();
	}

	@Override
	public CLKParameterList newParameterList() {
		return new CLKParameterListImpl();
	}

}
