package ananas.app.roadmap.util.kml.dom;

import ananas.app.roadmap.util.kml.IKMLObject;

public class KMLContainer extends KMLFeature {

	@Override
	public boolean appendChild(IKMLObject child) {
		if (child instanceof KMLFeature) {
			return true;
		} else {
			return super.appendChild(child);
		}
	}

}
