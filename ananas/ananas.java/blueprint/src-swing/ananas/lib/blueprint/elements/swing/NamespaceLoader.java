package ananas.lib.blueprint.elements.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

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

		// begin {

		ns.registerClass("position", IE_position.Wrapper.class,
				IE_position.Core.class);

		ns.registerClass("JFrame", IEJFrame.Wrapper.class, JFrame.class);
		ns.registerClass("JPanel", IEJPanel.Wrapper.class, JPanel.class);

		ns.registerClass("JButton", IEJButton.Wrapper.class, JButton.class);
		ns.registerClass("JLabel", IEJLabel.Wrapper.class, JLabel.class);

		ns.registerClass("JTextComponent", IEJTextComponent.Wrapper.class,
				JTextComponent.class);
		ns.registerClass("JTextField", IEJTextField.Wrapper.class,
				JTextField.class);
		ns.registerClass("JPasswordField", IEJPasswordField.Wrapper.class,
				JPasswordField.class);
		ns.registerClass("JTextArea", IEJTextArea.Wrapper.class,
				JTextArea.class);

		// } end

		return ns;

	}

}
