package ananas.app.roadmap.util.kml;

import org.w3c.dom.Element;

public interface IKMLObject {

	void bindTarget(KmlDoc document, Element element);

	Element getTargetElement();

	KmlDoc getTargetDocument();

}
