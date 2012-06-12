package ananas.app.ht4ad;

import ananas.app.ht4ad.jsapi2.DefaultRegisterChainNode;
import ananas.app.ht4ad.jsapi2.IJavascriptAPI2;
import ananas.app.ht4ad.jsapi2.IKeyValueMapper;
import ananas.app.ht4ad.jsapi2.IRegisterChainNode;
import android.app.Activity;

public class Ht4adActivityJsapiTools implements IJavascriptAPI2 {

	private final IRegisterChainNode mRegs = new DefaultRegisterChainNode();
	private final Activity mActivity;

	public Ht4adActivityJsapiTools(Activity activity) {
		this.mActivity = activity;
		this.mActivity.toString();// anti warning
	}

	public String get(String key) {
		return this.mRegs.get(key);
	}

	public void set(String key, String value) {
		this.mRegs.set(key, value);
	}

	public String[] listKeys() {
		return this.mRegs.listKeys();
	}

	public IKeyValueMapper getNext() {
		return this.mRegs.getNext();
	}

	public void setNext(IKeyValueMapper next) {
		this.mRegs.setNext(next);
	}

}
