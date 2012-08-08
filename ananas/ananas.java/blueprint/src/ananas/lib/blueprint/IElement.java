package ananas.lib.blueprint;

/**
 * this class MUST has a Default() constructor
 * */

public interface IElement {

	String getId();

	Object getTarget();

	IDocument getOwnerDocument();

	IClass getBlueprintClass();

	void bindOwnerDocument(IDocument ownerDoc);

	void bindBlueprintClass(IClass aClass);

	boolean setAttribute(String name, String value);

	boolean appendText(String text);

	boolean appendChild(IElement element);

}
