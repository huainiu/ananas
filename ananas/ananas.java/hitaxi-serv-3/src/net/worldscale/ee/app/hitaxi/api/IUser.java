package net.worldscale.ee.app.hitaxi.api;

public interface IUser {

	String getJID();

	String getNickname();

	String getPhoneNumber();

	// setter

	void setNickname(String nickname);

	void setPhoneNumber(String phoneNumber);

}
