package ananas.lib.blueprint.impl;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import ananas.lib.blueprint.IDocument;
import ananas.lib.blueprint.IDocumentBuilder;
import ananas.lib.blueprint.IImplementation;

final class ImplDocumentBuilder implements IDocumentBuilder {

	private final IImplementation mImpl;

	public ImplDocumentBuilder(IImplementation impl) {
		this.mImpl = impl;
	}

	@Override
	public IDocument build(InputStream is, String docURI) {
		try {
			IDocument doc = this.mImpl.newDocument(docURI);
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			MyCore dh = new MyCore(doc);
			parser.parse(is, dh);
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IDocument build(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDocument build(String docURI) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IImplementation getImplementation() {
		return this.mImpl;
	}

	private class MyCore extends DefaultHandler {

		private final IDocument mDoc;

		public MyCore(IDocument doc) {
			this.mDoc = doc;
		}
	}

}
