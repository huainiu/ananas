package net.worldscale.ee.app.hitaxi.api.impl;

import java.util.Hashtable;

import net.worldscale.ee.app.hitaxi.api.ICustomer;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.IUser;
import net.worldscale.ee.app.hitaxi.api.IUserManager;
import net.worldscale.ee.app.hitaxi.api.UserType;

class ImplUserManager implements IUserManager {

	private final Hashtable<String, ICustomer> mTableHS;
	private final Hashtable<String, ITaxi> mTableCS;

	public ImplUserManager() {
		this.mTableHS = new Hashtable<String, ICustomer>();
		this.mTableCS = new Hashtable<String, ITaxi>();
	}

	public ITaxi getTaxi(String jid) {
		return this.mTableCS.get(jid);
	}

	public ICustomer getCustomer(String jid) {
		return this.mTableHS.get(jid);
	}

	public IUser openUser(String jid, UserType type) {

		if (jid == null)
			return null;

		if (type.equals(UserType.type_taxi)) {
			ITaxi ret = this.mTableCS.get(jid);
			if (ret == null) {
				ret = new ImplUserForTaxi(jid);
				this.mTableCS.put(jid, ret);
			}
			return ret;

		} else if (type.equals(UserType.type_customer)) {
			ICustomer ret = this.mTableHS.get(jid);
			if (ret == null) {
				ret = new ImplUserForCustomer(jid);
				this.mTableHS.put(jid, ret);
			}
			return ret;
		}

		return null;
	}

	public IUser[] listUsers(int offset, int length) {
		return this.mTableCS.values().toArray(new IUser[0]);
	}

}
