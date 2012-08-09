package ananas.lib.blueprint;

import java.io.InputStream;

public interface IBlueprint {

	IImplementation getImplementation();

	IDocumentBuilderFactory getDocumentBuilderFactory();

	IDocument loadDocument(String docURI);

	IDocument loadDocument(InputStream is, String docURI);

}
