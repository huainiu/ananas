package ananas.app.roadmap.util.kml.dom;


public class KMLPoint extends KMLGeometry {

	public KML_coordinates mCoordinates;

	@Override
	public boolean appendChild(IKMLObject child) {

		if (child instanceof KML_coordinates) {
			this.mCoordinates = (KML_coordinates) child;

		} else {
			return super.appendChild(child);
		}

		return true;
	}

}
