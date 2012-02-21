package ws.xmpp_console.cmdlinekit.impl;

import java.util.Hashtable;

import ws.xmpp_console.cmdlinekit.CLKMutableParameterList;
import ws.xmpp_console.cmdlinekit.CLKParameter;
import ws.xmpp_console.cmdlinekit.CLKParameterList;

class CLKParameterListImpl implements CLKParameterList, CLKMutableParameterList {

	private final Hashtable<String, CLKParameter> mTable = new Hashtable<String, CLKParameter>();

	public CLKParameterListImpl() {
	}

	@Override
	public CLKParameter[] listParameters() {
		return this.mTable.values().toArray(new CLKParameter[0]);
	}

	@Override
	public CLKParameter getParameter(String name) {
		return this.mTable.get(name);
	}

	@Override
	public void addParameter(CLKParameter param) {
		if (param != null) {
			String name = param.getName();
			if (name != null) {
				this.mTable.put(name, param);
			}
		}
	}

}
