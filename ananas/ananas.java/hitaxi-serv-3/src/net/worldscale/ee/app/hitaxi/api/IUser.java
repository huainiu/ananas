package net.worldscale.ee.app.hitaxi.api;

public interface IUser {

	String getJID();

	String getNickname();

	String getPhoneNumber();

	double getLongitude();

	double getLatitude();

	// setter

	void setNickname(String nickname);

	void setPhoneNumber(String phoneNumber);

	void setLocation(double longitude, double latitude);

}
