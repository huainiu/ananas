package ananas.app.ht4ad.jsapi2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultRegisterChainNode implements IRegisterChainNode {

	private final Map<String, IKeyHandler> mMap;
	private IKeyValueMapper mNext;

	public DefaultRegisterChainNode() {
		this.mMap = new HashMap<String, IKeyHandler>();
	}

	public IKeyValueMapper getNext() {
		return this.mNext;
	}

	public void setNext(IKeyValueMapper next) {
		this.mNext = next;
	}

	public String get(String key) {
		if (key != null) {
			IKeyHandler hdr = this.mMap.get(key);
			if (hdr != null) {
				return hdr.onGet(this, key);
			} else {
				IKeyValueMapper next = this.mNext;
				if (next != null) {
					return next.get(key);
				}
			}
		}
		return null;
	}

	public void set(String key, String value) {
		if (key != null) {
			IKeyHandler hdr = this.mMap.get(key);
			if (hdr != null) {
				hdr.onSet(this, key, value);
			} else {
				IKeyValueMapper next = this.mNext;
				if (next != null) {
					next.set(key, value);
				}
			}
		}
	}

	public String[] listKeys() {
		Set<String> keyset = this.mMap.keySet();
		IKeyValueMapper next = this.mNext;
		if (next != null) {
			String[] keys = next.listKeys();
			for (String key : keys) {
				keyset.add(key);
			}
		}
		return keyset.toArray(new String[keyset.size()]);
	}

	public void setKeyHandler(String key, IKeyHandler handler) {
		if (key != null && handler != null) {
			this.mMap.put(key, handler);
		}
	}

	public IKeyHandler getKeyHandler(String key) {
		return this.mMap.get(key);
	}

}
