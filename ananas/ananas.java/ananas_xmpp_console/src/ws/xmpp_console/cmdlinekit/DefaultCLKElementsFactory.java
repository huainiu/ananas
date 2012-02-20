package ws.xmpp_console.cmdlinekit;

import ws.xmpp_console.cmdlinekit.impl.CLKElementsFactoryImpl;

public final class DefaultCLKElementsFactory {

	private final static CLKElementsFactoryImpl impl = new CLKElementsFactoryImpl();

	public static CLKElementsFactory getFactory() {
		return impl;
	}
}
