package net.worldscale.ee.app.hitaxi.api.impl;

import java.util.Hashtable;

import net.worldscale.ee.app.hitaxi.api.ICustomer;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.ITradeManager;
import net.worldscale.ee.app.hitaxi.api.IUser;

class ImplTradeMngr implements ITradeManager {

	private final Hashtable<String, MyPair> mTable;

	public ImplTradeMngr() {
		this.mTable = new Hashtable<String, MyPair>();
	}

	public void registerPair(ITaxi taxi, ICustomer customer) {
		if (taxi != null && customer != null) {
			MyPair pair = new MyPair(taxi, customer);
			String jid1 = taxi.getJID();
			String jid2 = taxi.getJID();
			if (jid1 != null && jid2 != null) {
				this.mTable.put(jid1, pair);
				this.mTable.put(jid2, pair);
			}
		}
	}

	public IUser getPair(IUser user) {
		String jid0 = user.getJID();
		MyPair pair = this.mTable.get(jid0);
		if (user instanceof ITaxi) {
			return pair.mCustomer;
		} else {
			return pair.mTaxi;
		}
	}

	public void unregisterPair(IUser user) {
		String jid0 = user.getJID();
		MyPair pair = this.mTable.get(jid0);
		String jid1 = pair.mCustomer.getJID();
		String jid2 = pair.mTaxi.getJID();
		this.mTable.remove(jid1);
		this.mTable.remove(jid2);
	}

	class MyPair {

		private final ICustomer mCustomer;
		private final ITaxi mTaxi;

		public MyPair(ITaxi taxi, ICustomer customer) {
			this.mTaxi = taxi;
			this.mCustomer = customer;
		}
	}

}
