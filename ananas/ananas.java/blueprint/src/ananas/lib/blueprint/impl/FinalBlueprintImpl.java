package ananas.lib.blueprint.impl;

import java.io.InputStream;

import ananas.lib.blueprint.Blueprint;
import ananas.lib.blueprint.IDocument;
import ananas.lib.blueprint.IDocumentBuilder;
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

	@Override
	public IDocument loadDocument(String docURI) {

		InputStream is = null;
		return this.loadDocument(is, docURI);

	}

	@Override
	public IDocument loadDocument(InputStream is, String docURI) {
		IImplementation impl = this.getImplementation();
		IDocumentBuilder dbf = this.getDocumentBuilderFactory()
				.createDocumentBuilder(impl);
		return dbf.build(is, docURI);
	}

}
