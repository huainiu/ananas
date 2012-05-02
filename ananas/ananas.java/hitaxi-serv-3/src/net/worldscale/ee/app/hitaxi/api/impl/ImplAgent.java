package net.worldscale.ee.app.hitaxi.api.impl;

import net.worldscale.ee.app.hitaxi.api.IAgent;
import net.worldscale.ee.app.hitaxi.api.IPositionMap;
import net.worldscale.ee.app.hitaxi.api.ITradeManager;
import net.worldscale.ee.app.hitaxi.api.IUserManager;

class ImplAgent implements IAgent {

	private static IAgent sInst = new ImplAgent();

	public static IAgent getInstance() {
		return sInst;
	}

	private final IPositionMap mPosMap = new ImplPosMap();
	private final ITradeManager mTradeMngr = new ImplTradeMngr();
	private final IUserManager mUserManager = new ImplUserManager();

	public IPositionMap getPosMap() {
		return this.mPosMap;
	}

	public ITradeManager getTradeManager() {
		return this.mTradeMngr;
	}

	public IUserManager getUserManager() {
		return this.mUserManager;
	}

}
