package ananas.app.roadmap.util.kml.dom;


public class KMLFeature extends KMLObject {

	public KML_name mName;
	public KML_description mDescription;

	@Override
	public boolean appendChild(IKMLObject child) {

		if (child instanceof KML_name) {
			this.mName = (KML_name) child;

		} else if (child instanceof KML_description) {
			this.mDescription = (KML_description) child;

		} else {
			return super.appendChild(child);
		}

		return true;
	}

}
