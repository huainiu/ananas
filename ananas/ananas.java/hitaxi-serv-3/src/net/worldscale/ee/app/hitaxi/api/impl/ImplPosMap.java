package net.worldscale.ee.app.hitaxi.api.impl;

import net.worldscale.ee.app.hitaxi.api.ICustomer;
import net.worldscale.ee.app.hitaxi.api.IPositionMap;
import net.worldscale.ee.app.hitaxi.api.ITaxi;

class ImplPosMap implements IPositionMap {

	final static int s_a = 0;
	final static int s_t = 1;
	final static int s_g = 2;
	final static int s_c = 3;

	private final MyConfig mConfig;
	private MyNode mRoot;

	public ImplPosMap() {
		this.mConfig = new MyConfig();
	}

	/**
	 * @param longitude
	 *            , [-180,180]
	 * @param latitude
	 *            , [-90,90]
	 * */

	public void setPos(ITaxi taxi, double longitude, double latitude) {
		taxi.setLocation(longitude, latitude);
		long nPath = this.coorToLong(longitude, latitude);
		MyNode node = this.mRoot;
		if (node == null) {
			node = new MyNode(null, 0);
			this.mRoot = node;
		}
		for (int i = this.mConfig.depth; i > 0; i--) {
			final int nStanza = (int) (nPath & 0x03);
			nPath >>= 2;
			MyNode nextNode;
			switch (nStanza) {
			case s_a: {
				nextNode = node.m_a;
				break;
			}
			case s_t: {
				nextNode = node.m_t;
				break;
			}
			case s_g: {
				nextNode = node.m_g;
				break;
			}
			case s_c: {
				nextNode = node.m_c;
				break;
			}
			default:
				nextNode = node;
			}
			if (nextNode == null) {
				nextNode = new MyNode(node, nStanza);
			}
			node = nextNode;
			node.m_taxi = taxi;
		}
	}

	public ITaxi[] findTaxi(ICustomer customer, double longitude,
			double latitude) {

		ITaxi[] array = new ITaxi[10];
		int count = this._findTaxi(longitude, latitude, array);
		if (count == array.length) {
			return array;
		} else {
			return array;
		}
	}

	private int _findTaxi(double longitude, double latitude, ITaxi[] result) {

		long nPath = this.coorToLong(longitude, latitude);
		MyNode node = this.mRoot;
		if (node == null) {
			return 0;
		}
		for (int i = this.mConfig.depth; i > 0; i--) {
			final int nStanza = (int) (nPath & 0x03);
			nPath >>= 2;
			MyNode nextNode;
			switch (nStanza) {
			case s_a: {
				nextNode = node.m_a;
				break;
			}
			case s_t: {
				nextNode = node.m_t;
				break;
			}
			case s_g: {
				nextNode = node.m_g;
				break;
			}
			case s_c: {
				nextNode = node.m_c;
				break;
			}
			default:
				nextNode = node;
			}
			if (nextNode == null) {
				break;
			} else {
				node = nextNode;
			}
		}
		ITaxi prevTaxi = null;
		int count = 0;
		for (; count < result.length;) {
			MyNode thisNode;
			if (node == null) {
				break;
			} else {
				thisNode = node;
				node = node.m_parent;
			}
			ITaxi taxi = thisNode.m_taxi;
			if (taxi != null) {
				if (!taxi.equals(prevTaxi)) {
					result[count++] = taxi;
					prevTaxi = taxi;
				}
			}
		}
		return count;
	}

	private long coorToLong(double lon, double lat) {
		final int one = 0x7fff0000;
		int intlon = (int) (((lon + 180.0) / 360.0) * one);
		int intlat = (int) (((lat + 90.0) / 180.0) * one);
		long rlt = 0;
		for (int i = 32; i > 0; i--) {
			rlt <<= 1;
			rlt |= (intlon & 0x01);
			rlt <<= 1;
			rlt |= (intlat & 0x01);
			//
			intlon >>= 1;
			intlat >>= 1;
		}
		return rlt;
	}

	static class MyNode {

		// final
		final int m_depth;
		final MyNode m_parent;
		final int m_pos;
		// var
		MyNode m_a = null;
		MyNode m_t = null;
		MyNode m_g = null;
		MyNode m_c = null;

		ITaxi m_taxi;

		public MyNode(MyNode parent, int pos) {
			this.m_parent = parent;
			this.m_pos = pos;
			if (parent == null) {
				this.m_depth = 0;
			} else {
				this.m_depth = parent.m_depth + 1;
				switch (pos) {
				case s_a: {
					parent.m_a = this;
					break;
				}
				case s_t: {
					parent.m_t = this;
					break;
				}
				case s_g: {
					parent.m_g = this;
					break;
				}
				case s_c: {
					parent.m_c = this;
					break;
				}
				default:
				}
			}
		}
	}

	static class MyConfig {

		final int depth = 20;

	}

}
