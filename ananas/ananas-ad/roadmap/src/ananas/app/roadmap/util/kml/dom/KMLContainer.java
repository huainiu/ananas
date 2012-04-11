package ananas.app.roadmap.util.kml.dom;


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
