package ananas.app.ht4ad.jsapi2;

public class DefaultKeyHandler implements IKeyHandler {

	private String mValue;

	public DefaultKeyHandler() {
	}

	public void onSet(IRegisterChainNode node, String key, String value) {
		this.mValue = value;
	}

	public String onGet(IRegisterChainNode node, String key) {
		String value = this.mValue;
		if (value == null) {
			IKeyValueMapper next = node.getNext();
			if (next != null) {
				value = next.get(key);
			}
		}
		return value;
	}

}
