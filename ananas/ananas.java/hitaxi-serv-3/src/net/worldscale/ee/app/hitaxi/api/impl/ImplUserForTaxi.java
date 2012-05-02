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

	public String getCarID() {
		return this.m_carId;
	}

	public void setCarID(String carId) {
		this.m_carId = carId;
	}

}
