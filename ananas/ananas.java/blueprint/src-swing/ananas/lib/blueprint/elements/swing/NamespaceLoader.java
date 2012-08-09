package ananas.lib.blueprint.elements.swing;

import javax.swing.JButton;
import javax.swing.JFrame;

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

		ns.registerClass("JButton", IEJButton.Wrapper.class, JButton.class);
		ns.registerClass("JFrame", IEJFrame.Wrapper.class, JFrame.class);

		return ns;

	}

}
