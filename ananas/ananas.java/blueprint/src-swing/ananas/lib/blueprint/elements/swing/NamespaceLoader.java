package ananas.lib.blueprint.elements.swing;

import javax.swing.JButton;
import javax.swing.JFrame;

import ananas.lib.blueprint.IImplementation;
import ananas.lib.blueprint.INamespace;
import ananas.lib.blueprint.INamespaceLoader;
import ananas.lib.blueprint.elements.awt.IE_position;

public class NamespaceLoader implements INamespaceLoader {

	public NamespaceLoader() {
	}

	@Override
	public INamespace load(IImplementation impl) {

		String nsURI = "xmlns:ananas:blueprint:swing";
		String defaultPrefix = "swing";
		INamespace ns = impl.newNamespace(nsURI, defaultPrefix);

		ns.registerClass("position", IE_position.Wrapper.class,
				IE_position.Core.class);
		ns.registerClass("JButton", IEJButton.Wrapper.class, JButton.class);
		ns.registerClass("JFrame", IEJFrame.Wrapper.class, JFrame.class);

		return ns;

	}

}
