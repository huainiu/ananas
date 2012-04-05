package ananas.roadmap;

import ananas.roadmap.service.DefaultRoadmapServiceConnector;
import ananas.roadmap.service.IRoadmapServiceConnector;
import ananas.roadmap.service.IRoadmapServiceConnector.ConnectionListener;
import ananas.roadmap.service.RoadmapService;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MapsActivity extends MapActivity implements ConnectionListener {

	private IRoadmapServiceConnector mServConn;
	private MapView mMapView;
	private ToggleButton mBtnGps;
	private MyLocationOverlay mMyLocOver;
	private Button mBtnMapTypePlain;
	private Button mBtnJumpToMyPos;
	private Button mBtnMapTypeSat;

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		this.setContentView(R.layout.ui_maps);
		this.mServConn = new DefaultRoadmapServiceConnector(this);
		this.mServConn.setConnectionListener(this);

		// zoom control
		this.mMapView = (MapView) findViewById(R.id.mapview);
		this.mMapView.setBuiltInZoomControls(true);
		MyLocationOverlay myloc = new MyLocationOverlay(this, this.mMapView);
		// myloc.enableMyLocation();
		this.mMapView.getOverlays().add(myloc);
		this.mMapView.getOverlays().add(new ArmScaleOverlay());
		this.mMyLocOver = myloc;

		// GPS on/off
		this.mBtnGps = (ToggleButton) this.findViewById(R.id.toggle_gps);
		this.mBtnJumpToMyPos = (Button) this
				.findViewById(R.id.button_jump_to_my_pos);
		this.mBtnMapTypePlain = (Button) this
				.findViewById(R.id.button_map_type_plain);
		this.mBtnMapTypeSat = (Button) this
				.findViewById(R.id.button_map_type_sat);

		this.mBtnGps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MapsActivity.this._switchGps();
			}
		});
		this.mBtnJumpToMyPos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MapsActivity.this._jumpToMyPos();
			}
		});
		this.mBtnMapTypePlain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MapsActivity.this.mMapView.setSatellite(false);
			}
		});
		this.mBtnMapTypeSat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MapsActivity.this.mMapView.setSatellite(true);
			}
		});

	}

	protected void _jumpToMyPos() {
		GeoPoint point = this.mMyLocOver.getMyLocation();
		if (point == null)
			return;
		this.mMapView.getController().animateTo(point);
	}

	protected void _switchGps() {
		boolean isOn = this.mBtnGps.isChecked();
		this.mServConn.getBinderEx().setGpsOn(isOn);
		if (isOn) {
			this.mMyLocOver.enableMyLocation();
		} else {
			this.mMyLocOver.disableMyLocation();
		}
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

	private static final int menu_item_exit = 2;
	private static final int menu_item_home = 3;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean ret = super.onCreateOptionsMenu(menu);
		int groupId = 1;
		int order = 1;
		menu.add(groupId, menu_item_exit, order, "exit");
		menu.add(groupId, menu_item_home, order, "home");
		return ret;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean ret = super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case menu_item_exit: {

			this.stopService(new Intent(this, RoadmapService.class));

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);

			break;
		}
		case menu_item_home: {
			Intent intent = new Intent(this, RoadmapActivity.class);
			this.startActivity(intent);
			break;
		}
		default:
		}
		return ret;
	}

	@Override
	public void onConnected(IRoadmapServiceConnector conn) {
		boolean isOn = conn.getBinderEx().isGpsOn();
		this.mBtnGps.setChecked(isOn);
		if (isOn) {
			this.mMyLocOver.enableMyLocation();
		} else {
			this.mMyLocOver.disableMyLocation();
		}
	}

	@Override
	public void onDisconnected(IRoadmapServiceConnector conn) {

	}

}
