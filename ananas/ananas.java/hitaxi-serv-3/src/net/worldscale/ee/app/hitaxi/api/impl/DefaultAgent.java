package net.worldscale.ee.app.hitaxi.api.impl;

import net.worldscale.ee.app.hitaxi.api.IAgent;

public class DefaultAgent {
	
	public static IAgent getInstance() {
		return ImplAgent.getInstance();
	}

}
