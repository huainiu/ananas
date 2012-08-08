package ananas.lib.blueprint;

public interface IClass {

	IElement createElement(IDocument ownerDoc);

	INamespace getOwnerNamespace();

	String getLocalName();

	Class<?> getElementClass();

	Class<?> getTargetClass();

}
