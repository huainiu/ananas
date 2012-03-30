package ananas.roadmap;

import ananas.roadmap.service.RoadmapService;
import ananas.roadmap.spi.IRoadmapServiceBinder;
import ananas.roadmap.spi.RoadmapServiceBinderProxy;
import ananas.roadmap.uikit.IActivityCenter;
import ananas.roadmap.uikit.IUIAgent;
import ananas.roadmap.uikit.impl.IUIAgentImpl;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.MapActivity;

public class RoadmapActivity extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this._startService();
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
	protected boolean isRouteDisplayed() {
		return false;
	}

	private void _startService() {
		Intent intent = new Intent(this, RoadmapService.class);
		this.startService(intent);
	}

	private void _bindService() {
		Intent intent = new Intent(this, RoadmapService.class);
		this.bindService(intent, this.mServiceConn, Context.BIND_AUTO_CREATE);
	}

	private void _unbindService() {
		this.unbindService(this.mServiceConn);
	}

	private final ServiceConnection mServiceConn = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			if (service instanceof IRoadmapServiceBinder) {
				IRoadmapServiceBinder binder = (IRoadmapServiceBinder) service;
				RoadmapActivity.this.mBinderProxy.setTarget(binder);
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			RoadmapActivity.this.mBinderProxy.setTarget(null);
		}
	};

	private final RoadmapServiceBinderProxy mBinderProxy = new RoadmapServiceBinderProxy();

	private static final int menu_item_a = 1;
	private static final int menu_item_b = 2;
	private static final int menu_item_c = 3;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean ret = super.onCreateOptionsMenu(menu);
		int groupId = 1;
		int order = 1;
		menu.add(groupId, menu_item_a, order, "a");
		menu.add(groupId, menu_item_b, order, "b");
		menu.add(groupId, menu_item_c, order, "c");
		return ret;
	}

	private final IActivityCenter mCenter = new IActivityCenter() {

		private IUIAgent mUIAgent = new IUIAgentImpl();

		@Override
		public IUIAgent getUIAgent() {
			return this.mUIAgent;
		}

		@Override
		public Activity getActivity() {
			return RoadmapActivity.this;
		}
	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean ret = super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case menu_item_a: {
			(new UI_a(mCenter)).show();
			break;
		}
		case menu_item_b: {
			(new UI_b(mCenter)).show();
			break;
		}
		case menu_item_c: {
			(new UI_c(mCenter)).show();
			break;
		}
		default:
		}
		return ret;
	}

}
