package net.worldscale.ee.app.hitaxi.api;

public interface IPositionMap {

	void setPos(ITaxi taxi, double longitude, double latitude);

	ITaxi[] findTaxi(ICustomer customer, double longitude, double latitude);

}
