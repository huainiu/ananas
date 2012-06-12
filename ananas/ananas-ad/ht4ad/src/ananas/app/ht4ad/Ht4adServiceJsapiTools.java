package ananas.app.ht4ad;

import ananas.app.ht4ad.jsapi2.DefaultMemoryChainNode;
import ananas.app.ht4ad.jsapi2.DefaultRegisterChainNode;
import ananas.app.ht4ad.jsapi2.DefaultStorageChainNode;
import ananas.app.ht4ad.jsapi2.IChainNode;
import ananas.app.ht4ad.jsapi2.IJavascriptAPI2;
import ananas.app.ht4ad.jsapi2.IKeyValueMapper;
import ananas.app.ht4ad.jsapi2.IRegisterChainNode;
import ananas.app.ht4ad.jsapi2.IStorageChainNode;
import android.app.Service;

public class Ht4adServiceJsapiTools implements IJavascriptAPI2 {

	private final IRegisterChainNode mRegNode = new DefaultRegisterChainNode();
	private final IStorageChainNode mStoNode = new DefaultStorageChainNode();
	private final IChainNode mMemNode = new DefaultMemoryChainNode();
	private final IChainNode mChainHeader;
	private final Service mService;

	public Ht4adServiceJsapiTools(Service service) {
		this.mService = service;
		this.mStoNode.setNext(this.mMemNode);
		this.mRegNode.setNext(this.mStoNode);
		this.mChainHeader = this.mRegNode;
		this.mService.toString();// anti warning
	}

	private IChainNode getChainHeader() {
		return this.mChainHeader;
	}

	public String get(String key) {
		return this.getChainHeader().get(key);
	}

	public void set(String key, String value) {
		this.getChainHeader().set(key, value);
	}

	public String[] listKeys() {
		return this.getChainHeader().listKeys();
	}

	public IKeyValueMapper getNext() {
		return this.getChainHeader().getNext();
	}

	public void setNext(IKeyValueMapper next) {

	}

}
