package ananas.app.roadmap.util.kml;

import org.w3c.dom.Element;

public class KML_obj_base implements IKMLObject {

	private Element mTargetElement;
	private KmlDoc mTargetDocument;

	public KML_obj_base() {
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
