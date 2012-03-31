package ananas.roadmap.service;

import ananas.android.jsonable.DefaultJsonable;
import ananas.roadmap.jsonable.cmd.JsonableBinderCommand;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

public class RoadmapService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return this.mBinderBase;
	}

	private final MyBaseBinder mBinderBase = new MyBaseBinder();

	class MyBaseBinder extends Binder implements IRoadmapServiceBinder {

		@Override
		public String invoke(String param) {
			JsonableBinderCommand cmd = (JsonableBinderCommand) DefaultJsonable
					.load(param);
			cmd.execute(RoadmapService.this.mBinderEx);
			return DefaultJsonable.save(cmd);
		}
	}

	private boolean mIsGpsOn;

	private final IRoadmapServiceBinderEx mBinderEx = new IRoadmapServiceBinderEx() {

		@Override
		public boolean isGpsOn() {
			return RoadmapService.this.mIsGpsOn;
		}

		@Override
		public void setGpsOn(boolean enable) {
			System.out.println(this + ".setGpsOn = " + enable);
			RoadmapService.this.mIsGpsOn = enable;
			LocationManager lm = (LocationManager) RoadmapService.this
					.getSystemService(Context.LOCATION_SERVICE);
			final LocationListener listenerH = RoadmapService.this.mLocationlistenerH;
			final LocationListener listenerL = RoadmapService.this.mLocationlistenerL;
			if (enable) {
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
						10, listenerH);
				lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
						5000, 100, listenerL);

				ILocationRecordOutputStream lros = new DefaultLocationRecordOutputStream();
				RoadmapService.this._setCurrentLROS(lros);

			} else {
				lm.removeUpdates(listenerH);
				lm.removeUpdates(listenerL);
				RoadmapService.this._setCurrentLROS(null);
			}
		}

	};

	private ILocationRecordOutputStream mCurLROS;

	private void _setCurrentLROS(ILocationRecordOutputStream lros) {
		final ILocationRecordOutputStream pnew = lros;
		final ILocationRecordOutputStream pold;
		synchronized (this) {
			pold = this.mCurLROS;
			this.mCurLROS = pnew;
		}
		if (pold != null) {
			pold.close();
		}
		if (pnew != null) {
		}
	}

	private final LocationListener mLocationlistenerH = new MyLocationlistener();
	private final LocationListener mLocationlistenerL = new MyLocationlistener();

	private class MyLocationlistener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			System.out.println(this + ".onLocationChanged():" + location);
			ILocationRecordOutputStream lros = RoadmapService.this.mCurLROS;
			if (lros != null)
				lros.write(location);
		}

		@Override
		public void onProviderDisabled(String provider) {
			System.out.println(this + ".onProviderDisabled()");
		}

		@Override
		public void onProviderEnabled(String provider) {
			System.out.println(this + ".onProviderEnabled()");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}
	};

}
