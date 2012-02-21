package ws.xmpp_console.cmdlinekit.impl;

import ws.xmpp_console.cmdlinekit.CLKElementsFactory;
import ws.xmpp_console.cmdlinekit.CLKMutableCommandSet;
import ws.xmpp_console.cmdlinekit.CLKMutableParameter;
import ws.xmpp_console.cmdlinekit.CLKMutableParameterList;
import ws.xmpp_console.cmdlinekit.CLKRunLoop;

public class CLKElementsFactoryImpl implements CLKElementsFactory {

	@Override
	public CLKMutableCommandSet newCommandSet() {
		return new CLKCommandSetImpl();
	}

	@Override
	public CLKRunLoop newRunLoop() {
		return new CLKRunLoopImpl();
	}

	@Override
	public CLKMutableParameterList newParameterList() {
		return new CLKParameterListImpl();
	}

	@Override
	public CLKMutableParameter newParameter(String name) {
		return new CLKParameterImpl(name);
	}

	@Override
	public CLKMutableParameter newParameter(String name, String value,
			boolean isOption, String description) {

		CLKParameterImpl param = new CLKParameterImpl(name);
		param.setDescription(description);
		param.setOption(isOption);
		param.setValue(value);
		return param;
	}

}
