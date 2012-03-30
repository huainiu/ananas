package ananas.roadmap.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class DefaultRoadmapServiceConnector implements IRoadmapServiceConnector {

	private final Context mContext;
	private final RoadmapServiceBinderProxy mBinderProxy = new RoadmapServiceBinderProxy();

	public DefaultRoadmapServiceConnector(Context context) {
		this.mContext = context;
	}

	@Override
	public void connect() {
		this._bindService();

	}

	@Override
	public void disconnect() {
		this._unbindService();
	}

	/*
	 * private void _startService() { Intent intent = new Intent(this.mContext,
	 * RoadmapService.class); this.mContext.startService(intent); }
	 */
	private void _bindService() {
		Intent intent = new Intent(this.mContext, RoadmapService.class);
		this.mContext.bindService(intent, this.mServiceConn,
				Context.BIND_AUTO_CREATE);
	}

	private void _unbindService() {
		this.mContext.unbindService(this.mServiceConn);
	}

	private final ServiceConnection mServiceConn = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			if (service instanceof IRoadmapServiceBinder) {
				IRoadmapServiceBinder binder = (IRoadmapServiceBinder) service;
				DefaultRoadmapServiceConnector.this.mBinderProxy.setTarget(binder);
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			DefaultRoadmapServiceConnector.this.mBinderProxy.setTarget(null);
		}
	};

}
