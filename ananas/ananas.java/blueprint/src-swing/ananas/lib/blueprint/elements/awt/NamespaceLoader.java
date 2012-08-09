package ananas.lib.blueprint.elements.awt;

import ananas.lib.blueprint.IImplementation;
import ananas.lib.blueprint.INamespace;
import ananas.lib.blueprint.INamespaceLoader;

public class NamespaceLoader implements INamespaceLoader {

	public NamespaceLoader() {
	}

	@Override
	public INamespace load(IImplementation impl) {

		String nsURI = "xmlns:ananas:blueprint:awt";
		String defaultPrefix = "awt";
		INamespace ns = impl.newNamespace(nsURI, defaultPrefix);

		this.registerClass(ns, "Object", IEObject.Wrapper.class);
		this.registerClass(ns, "Component", IEComponent.Wrapper.class);
		this.registerClass(ns, "Container", IEContainer.Wrapper.class);
		this.registerClass(ns, "Window", IEWindow.Wrapper.class);
		this.registerClass(ns, "Frame", IEFrame.Wrapper.class);

		return ns;

	}

	private void registerClass(INamespace ns, String localName, Class<?> class1) {
		ns.registerClass(localName, class1, class1);
	}

}
