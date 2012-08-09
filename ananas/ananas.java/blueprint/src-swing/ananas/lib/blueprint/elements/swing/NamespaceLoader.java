package ananas.lib.blueprint.elements.swing;

import ananas.lib.blueprint.IImplementation;
import ananas.lib.blueprint.INamespace;
import ananas.lib.blueprint.INamespaceLoader;

public class NamespaceLoader implements INamespaceLoader {

	public NamespaceLoader() {
	}

	@Override
	public INamespace load(IImplementation impl) {

		String nsURI = "xmlns:ananas:blueprint:swing";
		String defaultPrefix = "swing";
		INamespace ns = impl.newNamespace(nsURI, defaultPrefix);

		this.registerClass(ns, "jframe", IEJFrame.EJFrame.class);

		return ns;

	}

	private void registerClass(INamespace ns, String localName, Class<?> class1) {
		ns.registerClass(localName, class1, class1);
	}

}
