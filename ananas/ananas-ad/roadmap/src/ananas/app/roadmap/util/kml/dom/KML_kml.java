package ananas.app.roadmap.util.kml.dom;


public class KML_kml extends KML_obj_base {

	public KMLFeature mFeature;

	public KML_kml() {
	}

	@Override
	public boolean appendChild(IKMLObject child) {

		if (child instanceof KMLFeature) {
			this.mFeature = (KMLFeature) child;

		} else {
			return super.appendChild(child);
		}

		return true;
	}

}
