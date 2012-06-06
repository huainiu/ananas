package ananas.app.ht4ad.jsapi2;

public interface IRegisterChainNode extends IChainNode {

	void setKeyHandler(String key, IKeyHandler handler);

	IKeyHandler getKeyHandler(String key);
}
