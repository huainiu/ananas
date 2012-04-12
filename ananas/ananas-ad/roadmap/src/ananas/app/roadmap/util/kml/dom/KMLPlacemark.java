package ananas.app.roadmap.util.kml.dom;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class KMLPlacemark extends KMLFeature {

	public KMLGeometry mGeometry;
	private OverlayItem mOverlayItem;

	@Override
	public boolean appendChild(IKMLObject child) {

		if (child instanceof KMLGeometry) {
			this.mGeometry = (KMLGeometry) child;

		} else {
			return super.appendChild(child);
		}

		return true;
	}

	public OverlayItem getOverlayItem() {
		if (this.mOverlayItem == null) {

			GeoPoint point = new GeoPoint(0, 0);
			String title = "";
			String text = "";
			this.mOverlayItem = new OverlayItem(point, title, text);

		}
		return this.mOverlayItem;
	}

	@Override
	public void listOverlayItems(IOverlayItemEnumerator enumerator) {
		OverlayItem item = this.getOverlayItem();
		enumerator.append(item);
	}

}
