package ananas.app.roadmap.util.kml.dom;

import org.w3c.dom.Element;

import ananas.app.roadmap.util.kml.IKMLObject;
import ananas.app.roadmap.util.kml.KmlDoc;

public class KMLObject implements IKMLObject {

	private Element mTargetElement;
	private KmlDoc mTargetDocument;

	public KMLObject() {
	}

	@Override
	public void bindTarget(KmlDoc document, Element element) {
		this.mTargetDocument = document;
		this.mTargetElement = element;
	}

	@Override
	public Element getTargetElement() {
		return this.mTargetElement;
	}

	@Override
	public KmlDoc getTargetDocument() {
		return this.mTargetDocument;
	}

}
