package net.worldscale.ee.app.hitaxi.api.impl;

import java.util.Hashtable;

import net.worldscale.ee.app.hitaxi.api.ICustomer;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.IUser;
import net.worldscale.ee.app.hitaxi.api.IUserManager;
import net.worldscale.ee.app.hitaxi.api.UserType;

class ImplUserManager implements IUserManager {

	private final Hashtable<String, IUser> mTable;

	public ImplUserManager() {
		this.mTable = new Hashtable<String, IUser>();
	}

	public ITaxi getTaxi(String jid) {
		IUser ret = this.mTable.get(jid);
		if (ret instanceof ITaxi) {
			return ((ITaxi) ret);
		} else {
			return null;
		}
	}

	public ICustomer getCustomer(String jid) {
		IUser ret = this.mTable.get(jid);
		if (ret instanceof ICustomer) {
			return ((ICustomer) ret);
		} else {
			return null;
		}
	}

	public IUser openUser(String jid, UserType type) {
		if (jid == null)
			return null;
		IUser ret = this.mTable.get(jid);
		if (type.equals(UserType.type_taxi) && (!(ret instanceof ITaxi))) {
			ret = new ImplUserForTaxi(jid);
			this.mTable.put(jid, ret);
		} else if (type.equals(UserType.type_customer)
				&& (!(ret instanceof ICustomer))) {
			ret = new ImplUserForCustomer(jid);
			this.mTable.put(jid, ret);
		}
		return ret;
	}

	public IUser[] listUsers(int offset, int length) {
		return this.mTable.values().toArray(new IUser[0]);
	}

}
