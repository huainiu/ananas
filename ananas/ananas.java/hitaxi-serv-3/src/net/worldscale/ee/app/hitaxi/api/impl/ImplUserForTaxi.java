package net.worldscale.ee.app.hitaxi.api.impl;

import net.worldscale.ee.app.hitaxi.api.ITaxi;

public class ImplUserForTaxi implements ITaxi {

	private final String m_jid;
	private String m_carId;
	private String m_nickname;
	private String m_phoneNum;

	public ImplUserForTaxi(String jid) {
		this.m_jid = jid;
	}

	public String getJID() {
		return this.m_jid;
	}

	public String getNickname() {
		return this.m_nickname;
	}

	public String getPhoneNumber() {
		return this.m_phoneNum;
	}

	public void setNickname(String nickname) {
		this.m_nickname = nickname;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.m_phoneNum = phoneNumber;
	}

	public String getNumberPlate() {
		return this.m_carId;
	}

	public void setNumberPlate(String carId) {
		this.m_carId = carId;
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
