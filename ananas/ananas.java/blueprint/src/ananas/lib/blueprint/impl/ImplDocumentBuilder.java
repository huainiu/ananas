package ananas.lib.blueprint.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ananas.lib.blueprint.IClass;
import ananas.lib.blueprint.IDocument;
import ananas.lib.blueprint.IDocumentBuilder;
import ananas.lib.blueprint.IElement;
import ananas.lib.blueprint.IImplementation;
import ananas.lib.blueprint.INamespace;
import ananas.lib.blueprint.INamespaceRegistrar;

final class ImplDocumentBuilder implements IDocumentBuilder {

	private final IImplementation mImpl;

	public ImplDocumentBuilder(IImplementation impl) {
		this.mImpl = impl;
	}

	@Override
	public IDocument build(InputStream is, String docURI) {
		try {
			IDocument doc = this.mImpl.newDocument(docURI);
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setNamespaceAware(true);
			SAXParser parser = parserFactory.newSAXParser();
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

		private final Stack<IElement> mStack;
		private final IDocument mDoc;
		private Locator mLocator;
		private INamespaceRegistrar mNSReg;

		public MyCore(IDocument doc) {
			this.mDoc = doc;
			this.mStack = new Stack<IElement>();
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {

			if (this.mStack.isEmpty()) {
				return;
			}
			final IElement element = this.mStack.peek();

			int from = start;
			int to = start + length;
			for (; from < to; from++) {
				final char c = ch[from];
				if (!this._isSpace(c))
					break;
			}
			for (; from < to; to--) {
				final char c = ch[to - 1];
				if (!this._isSpace(c))
					break;
			}
			if (from >= to)
				return;

			String text = new String(ch, start, length);
			boolean rlt = element.appendText(text);
			if (!rlt) {
				String msg = "The ELEMENT not accept the TEXT";
				System.err.println(msg + this._stringOfLocator());
				System.err.println("    " + "element = " + element);
				System.err.println("    " + "text    = " + text);
				throw new SAXException(msg);
			}

		}

		private boolean _isSpace(char c) {
			switch (c) {
			case 0:
			case ' ':
			case 0x09:
			case 0x0a:
			case 0x0d:
				return true;
			default:
				return false;
			}
		}

		@Override
		public void endDocument() throws SAXException {
			this.mStack.clear();
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {

			final IElement child = this.mStack.pop();
			child.tagEnd();
			if (this.mStack.isEmpty()) {
				this.mDoc.setRootElement(child);
				child.setParent(null);
			} else {
				final IElement parent = this.mStack.peek();
				child.setParent(parent);
				boolean rlt = parent.appendChild(child);
				if (!rlt) {
					String msg = "The PARENT not accept the CHILD";
					System.err.println(msg + this._stringOfLocator());
					System.err.println("    " + "parent = " + parent);
					System.err.println("    " + "child  = " + child);
					throw new SAXException(msg);
				}
			}
		}

		private String _stringOfLocator() {
			Locator locator = this.mLocator;
			if (locator == null)
				return "";
			return ("(line:" + locator.getLineNumber() + ", col:"
					+ locator.getColumnNumber() + ")");
		}

		@Override
		public void error(SAXParseException exception) throws SAXException {
			System.err.println("error:" + exception);
		}

		@Override
		public void setDocumentLocator(Locator locator) {
			this.mLocator = locator;
		}

		@Override
		public void startDocument() throws SAXException {
			this.mStack.clear();
			this.mDoc.setRootElement(null);
			this.mNSReg = this.mDoc.getImplementation().getNamespaceRegistrar();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attr) throws SAXException {

			INamespace ns = this.mNSReg.getNamespace(uri);
			if (ns == null) {
				String msg = "No Namespace";
				System.err.println(msg + this._stringOfLocator());
				System.err.println("    " + "Namespace-URI = " + uri);
				System.err.println("    " + "Local-Name    = " + localName);
				throw new SAXException(msg);
			}
			IClass cls = ns.findClass(localName);
			if (cls == null) {
				String msg = "No Class";
				System.err.println(msg + this._stringOfLocator());
				System.err.println("    " + "Namespace-URI = " + uri);
				System.err.println("    " + "Local-Name    = " + localName);
				throw new SAXException(msg);
			}
			IElement element = cls.createElement(this.mDoc);

			this.mStack.push(element);
			element.tagBegin();

			final int len = attr.getLength();
			for (int i = 0; i < len; i++) {
				String name = attr.getQName(i);
				String value = attr.getValue(i);
				boolean rlt = element.setAttribute(name, value);
				if (!rlt) {
					String msg = "The element not accept the attribute";
					System.err.println(msg + this._stringOfLocator());
					System.err.println("    " + "element   = " + element);
					System.err.println("    " + "attrName  = " + name);
					System.err.println("    " + "attrValue = " + value);
					throw new SAXException(msg);
				}
			}

		}

		@Override
		public void warning(SAXParseException exception) throws SAXException {
			System.err.println("warning:" + exception);
		}
	}
}
