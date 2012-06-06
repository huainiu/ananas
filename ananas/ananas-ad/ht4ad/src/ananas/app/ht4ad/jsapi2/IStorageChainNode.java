package ananas.app.ht4ad.jsapi2;

import java.io.File;

public interface IStorageChainNode extends IChainNode {

	void load(File file);

	void save(File file);

}
