package net.worldscale.ee.app.hitaxi.api;

public interface ITradeManager {

	void registerPair(ITaxi taxi, ICustomer customer);

	IUser getPair(IUser user);

	void unregisterPair(IUser user);

}
