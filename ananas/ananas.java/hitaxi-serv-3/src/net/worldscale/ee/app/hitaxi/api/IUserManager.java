package net.worldscale.ee.app.hitaxi.api;

public interface IUserManager {

	ITaxi getTaxi(String jid);

	ICustomer getCustomer(String jid);

	IUser openUser(String jid, UserType type);

	IUser[] listUsers(int offset, int length);

}
