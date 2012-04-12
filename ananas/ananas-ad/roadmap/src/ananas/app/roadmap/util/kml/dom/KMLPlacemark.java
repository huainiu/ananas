package ananas.app.roadmap.util.kml.dom;

import com.google.android.maps.OverlayItem;

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

	public OverlayItem getOverlayItem() {
		return null;
	}

	@Override
	public void listOverlayItems(IOverlayItemEnumerator enumerator) {
		OverlayItem item = this.getOverlayItem();
		enumerator.append(item);
	}

}
