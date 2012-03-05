package ws.runloop.impl;

import ws.runloop.RunLoopManager;

public class RunLoopAgent {

	private final static RunLoopManager sInst = new ImplRunLoopManager();

	public static RunLoopManager getRunLoopManager() {
		return sInst;
	}

}
