package ananas.lib.blueprint.elements.base;

import ananas.lib.blueprint.IImplementation;
import ananas.lib.blueprint.INamespace;
import ananas.lib.blueprint.INamespaceLoader;

public class NamespaceLoader implements INamespaceLoader {

	public NamespaceLoader() {
	}

	@Override
	public INamespace load(IImplementation impl) {

		String nsURI = "xmlns:ananas:blueprint:base";
		String defaultPrefix = "bp";
		INamespace ns = impl.newNamespace(nsURI, defaultPrefix);

		ns.registerClass("blueprint", BlueprintElement.class, Blueprint.class);
		ns.registerClass("document", DocumentElement.class, Document.class);
		ns.registerClass("import", ImportElement.class, Import.class);

		return ns;

	}

}
