package ananas.app.roadmap.util.kml.dom;

import ananas.app.roadmap.util.kml.IKMLObject;

public class KMLPlacemark extends KMLFeature {

	public KMLGeometry mGeometry;

	@Override
	public boolean appendChild(IKMLObject child) {

		if (child instanceof KMLGeometry) {
			this.mGeometry = (KMLGeometry) child;

		} else {
			return super.appendChild(child);
		}

		return true;
	}

}
