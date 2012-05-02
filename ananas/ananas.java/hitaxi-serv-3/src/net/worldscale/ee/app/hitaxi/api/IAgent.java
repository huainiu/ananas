package net.worldscale.ee.app.hitaxi.api;

public interface IAgent {

	IPositionMap getPosMap();

	ITradeManager getTradeManager();

	IUserManager getUserManager();

}
