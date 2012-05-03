package net.worldscale.ee.app.hitaxi.api.impl;

import net.worldscale.ee.app.hitaxi.api.ICustomer;

public class ImplUserForCustomer implements ICustomer {

	private final String m_jid;
	private String mPhoneNum;
	private String mNickname;

	public ImplUserForCustomer(String jid) {
		this.m_jid = jid;
	}

	public String getJID() {
		return this.m_jid;
	}

	public String getNickname() {
		return this.mNickname;
	}

	public String getPhoneNumber() {
		return this.mPhoneNum;
	}

	public void setNickname(String nickname) {
		this.mNickname = nickname;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.mPhoneNum = phoneNumber;
	}

	private double m_lon;
	private double m_lat;

	@Override
	public double getLongitude() {
		return this.m_lon;
	}

	@Override
	public double getLatitude() {
		return this.m_lat;
	}

	@Override
	public void setLocation(double longitude, double latitude) {
		this.m_lat = latitude;
		this.m_lon = longitude;
	}

}
