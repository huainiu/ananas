package ananas.lib.blueprint;

public class DefaultElement implements IElement {

	private IDocument mOwnerDoc;
	private IClass mBpClass;
	private String mId;

	public DefaultElement() {
	}

	@Override
	public Object getTarget() {
		return this;
	}

	@Override
	public IDocument getOwnerDocument() {
		return this.mOwnerDoc;
	}

	@Override
	public void bindOwnerDocument(IDocument doc) {
		if (doc != null && this.mOwnerDoc == null) {
			this.mOwnerDoc = doc;
		}
	}

	@Override
	public IClass getBlueprintClass() {
		return this.mBpClass;
	}

	@Override
	public void bindBlueprintClass(IClass bpClass) {
		if (bpClass != null && this.mBpClass == null) {
			this.mBpClass = bpClass;
		}
	}

	@Override
	public boolean setAttribute(String name, String value) {
		if ("id".equals(name)) {
			this.mId = value;
			this.mOwnerDoc.registerElement(this);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean appendText(String text) {
		return false;
	}

	@Override
	public boolean appendChild(IElement element) {
		return false;
	}

	@Override
	public String getId() {
		return this.mId;
	}

}
