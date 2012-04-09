package ananas.roadmap;

import java.util.Vector;

import ananas.roadmap.RoadmapService2.IRoadmapService2Binder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class RoadmapActivity2 extends MapActivity {

	private MapView mMapView;
	private MyLocationOverlay mMyLocOver;
	private boolean mIsVisibleMyPos;

	class Const {

		public static final String bool_true = "yes";
		public static final String bool_false = "no";
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.ui_maps_2);
		this._startService();

		//
		this.mMapView = (MapView) this.findViewById(R.id.mapview);

		this._initMapView();
	}

	private void _initMapView() {

		this.mMapView = (MapView) findViewById(R.id.mapview);
		this.mMapView.setBuiltInZoomControls(true);
		MyLocationOverlay myloc = new MyLocationOverlay(this, this.mMapView);
		// myloc.enableMyLocation();
		this.mMapView.getOverlays().add(myloc);
		this.mMapView.getOverlays().add(new ArmScaleOverlay());
		this.mMyLocOver = myloc;

	}

	@Override
	protected void onStart() {
		super.onStart();
		this._bindService();
	}

	@Override
	protected void onStop() {
		this._unbindService();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		final boolean rlt = super.onCreateOptionsMenu(menu);
		final Vector<Integer> v = new Vector<Integer>();

		v.add(R.string.menu_item_show_mypos);
		v.add(R.string.menu_item_goto_mypos);
		v.add(R.string.menu_item_rec_mypos);

		v.add(R.string.menu_item_map_type_map);
		v.add(R.string.menu_item_map_type_sat);

		v.add(R.string.menu_item_exit);

		for (Integer item : v) {
			int groupId = 0;
			int itemId = item;
			int order = 0;
			int titleRes = item;
			menu.add(groupId, itemId, order, titleRes);
		}
		return rlt;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.string.menu_item_goto_mypos: {
			RoadmapActivity2.this._gotoMyPos();
			break;
		}
		case R.string.menu_item_show_mypos: {
			RoadmapActivity2.this._showMyPos();
			break;
		}
		case R.string.menu_item_rec_mypos: {
			RoadmapActivity2.this._recMyPos();
			break;
		}
		case R.string.menu_item_map_type_map: {
			RoadmapActivity2.this.mMapView.setSatellite(false);
			break;
		}
		case R.string.menu_item_map_type_sat: {
			RoadmapActivity2.this.mMapView.setSatellite(true);
			break;
		}
		case R.string.menu_item_exit: {
			RoadmapActivity2.this._exitApp();
			break;
		}
		default:
		}
		return super.onOptionsItemSelected(item);
	}

	private void _exitApp() {

		this.mMyLocOver.disableMyLocation();
		this.mMyLocOver.disableCompass();

		this.mBinder.exit();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}

	private void _recMyPos() {
		String cur = this.mBinder.currentRecording();
		if (cur == null) {
			this.mBinder.startRecording();
		} else {
			this.mBinder.stopRecording();
		}
	}

	private void _gotoMyPos() {
		GeoPoint point = this.mMyLocOver.getMyLocation();
		if (point == null)
			return;
		this.mMapView.getController().animateTo(point);
	}

	private void _showMyPos() {
		mIsVisibleMyPos = !mIsVisibleMyPos;
		if (this.mIsVisibleMyPos) {
			this.mMyLocOver.enableMyLocation();
			this.mMyLocOver.enableCompass();
		} else {
			this.mMyLocOver.disableCompass();
			this.mMyLocOver.disableMyLocation();
		}
	}

	private void _startService() {
		Intent intent = new Intent(this, RoadmapService2.class);
		this.startService(intent);
	}

	private void _bindService() {
		Intent intent = new Intent(this, RoadmapService2.class);
		this.bindService(intent, this.mServConn, Context.BIND_AUTO_CREATE);
	}

	private void _unbindService() {
		this.unbindService(this.mServConn);
	}

	private RoadmapService2.IRoadmapService2Binder mBinder;

	private final ServiceConnection mServConn = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			RoadmapService2.IRoadmapService2Binder binder = (IRoadmapService2Binder) service;
			RoadmapActivity2.this.mBinder = binder;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			RoadmapActivity2.this.mBinder = null;
		}
	};

}
