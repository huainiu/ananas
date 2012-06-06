package ananas.app.ht4ad.jsapi2;

public interface IKeyHandler {

	void onSet(IRegisterChainNode node, String key, String value);

	String onGet(IRegisterChainNode node, String key);

}
