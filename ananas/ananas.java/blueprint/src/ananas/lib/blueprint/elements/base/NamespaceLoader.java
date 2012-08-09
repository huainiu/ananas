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

		this.registerClass(ns, "blueprint", ElementBlueprint.class);
		this.registerClass(ns, "content", ElementContent.class);
		this.registerClass(ns, "import", ElementImport.class);

		return ns;

	}

	private void registerClass(INamespace ns, String localName, Class<?> class1) {
		ns.registerClass(localName, class1, class1);
	}

}
