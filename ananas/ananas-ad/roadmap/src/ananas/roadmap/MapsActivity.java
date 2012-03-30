package ananas.roadmap;

import ananas.roadmap.service.IRoadmapServiceConnector;
import ananas.roadmap.service.DefaultRoadmapServiceConnector;
import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class MapsActivity extends MapActivity {

	private IRoadmapServiceConnector mServConn;

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		this.setContentView(R.layout.ui_maps);
		this.mServConn = new DefaultRoadmapServiceConnector(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		this.mServConn.connect();
	}

	@Override
	protected void onStop() {
		this.mServConn.disconnect();
		super.onStop();
	}

}
