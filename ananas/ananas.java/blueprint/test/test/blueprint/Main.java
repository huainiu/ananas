package test.blueprint;

import java.io.InputStream;

import javax.swing.JFrame;

import ananas.lib.blueprint.Blueprint;
import ananas.lib.blueprint.IDocument;
import ananas.lib.blueprint.IDocumentBuilder;
import ananas.lib.blueprint.IImplementation;

public class Main {

	public static void main(String[] args) {

		IImplementation impl = Blueprint.getInstance().getImplementation();
		IDocumentBuilder builder = Blueprint.getInstance()
				.getDocumentBuilderFactory().createDocumentBuilder(impl);
		String path = "/test.xml";
		InputStream is = "".getClass().getResourceAsStream(path);
		String baseURI = null;
		IDocument doc = builder.build(is, baseURI);
		// IElement root = doc.getRootElement();
		// System.out.println("doc:root = " + doc + ":" + root);

		JFrame frame = (JFrame) doc.findElementById("root").getTarget();
		frame.setVisible(true);

		// frame.setSize(200, 200);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
