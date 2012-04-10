package ananas.app.roadmap.util.kml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ananas.app.roadmap.util.kml.dom.KMLFeature;

public class KML_kml extends KML_obj_base {

	private KMLFeature mFeature;

	public KML_kml() {
	}

	public KMLFeature getFeature() {
		if (this.mFeature == null) {
			KmlDoc doc = this.getTargetDocument();
			Element element = this.getTargetElement();
			NodeList chs = element.getChildNodes();
			for (int i = chs.getLength() - 1; i >= 0; i--) {
				Node ch = chs.item(i);
				if (ch instanceof Element) {
					IKMLObject chObj = doc.getKMLObjectForElement((Element) ch);
					if (chObj instanceof KMLFeature) {
						this.mFeature = (KMLFeature) chObj;
						break;
					}
				}
			}
		}
		return this.mFeature;
	}

}
