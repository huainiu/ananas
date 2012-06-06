package ananas.app.ht4ad.jsapi2;

public interface IChainNode extends IKeyValueMapper {

	IKeyValueMapper getNext();

	void setNext(IKeyValueMapper next);

}
