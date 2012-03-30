package ananas.roadmap;

import ananas.roadmap.service.DefaultRoadmapServiceConnector;
import ananas.roadmap.service.IRoadmapServiceConnector;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

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

		// zoom control
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		// GPS on/off
		ToggleButton btn_gps = (ToggleButton) this
				.findViewById(R.id.toggle_gps);
		btn_gps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MapsActivity.this._switchGps();
			}
		});

	}

	protected void _switchGps() {
		boolean ison = this.mServConn.getBinderEx().isGpsOn();
		this.mServConn.getBinderEx().setGpsOn(!ison);
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
