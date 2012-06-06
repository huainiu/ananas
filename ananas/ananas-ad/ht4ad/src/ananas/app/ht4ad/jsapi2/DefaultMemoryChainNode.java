package ananas.app.ht4ad.jsapi2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultMemoryChainNode implements IChainNode {

	private final Map<String, String> mMap;
	private IKeyValueMapper mNext;

	public DefaultMemoryChainNode() {
		this.mMap = new HashMap<String, String>();
	}

	public String get(String key) {
		if (key == null) {
			return null;
		}
		String value = this.mMap.get(key);
		if (value == null) {
			IKeyValueMapper next = this.mNext;
			if (next != null) {
				value = next.get(key);
			}
		}
		return value;
	}

	public void set(String key, String value) {
		if (key != null) {
			if (value == null) {
				this.mMap.remove(key);
			} else {
				this.mMap.put(key, value);
			}
		}
	}

	public IKeyValueMapper getNext() {
		return this.mNext;
	}

	public void setNext(IKeyValueMapper next) {
		this.mNext = next;
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

}
