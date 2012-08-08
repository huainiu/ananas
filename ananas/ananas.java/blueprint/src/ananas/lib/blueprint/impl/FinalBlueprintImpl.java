package ananas.lib.blueprint.impl;

import ananas.lib.blueprint.Blueprint;
import ananas.lib.blueprint.IDocumentBuilderFactory;
import ananas.lib.blueprint.IImplementation;

public final class FinalBlueprintImpl extends Blueprint {

	final static IImplementation sImpl;
	final static IDocumentBuilderFactory sDBF;

	static {
		sImpl = new ImplImplementation();
		sDBF = new ImplDocumentBuilderFactory();
	}

	@Override
	public IImplementation getImplementation() {
		return sImpl;
	}

	@Override
	public IDocumentBuilderFactory getDocumentBuilderFactory() {
		return sDBF;
	}

}
