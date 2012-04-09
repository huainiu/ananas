package ananas.roadmap;

import java.util.Hashtable;

import ananas.roadmap.service.RecordSession;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;

public class RoadmapService2 extends Service {

	public static interface IRoadmapService2Binder {

		String startRecording();

		String stopRecording();

		String currentRecording();

		void setProperty(String key, String value);

		String getProperty(String key);

		void exit();
	}

	private final MyBinder mBinder = new MyBinder();
	private final Hashtable<String, String> mProperties;
	private RecordSession mCurRec;

	public RoadmapService2() {
		this.mProperties = new Hashtable<String, String>();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return this.mBinder;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this._startForeground();
	}

	@Override
	public void onDestroy() {
		this._setCurRec(null);
		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	private class MyBinder extends Binder implements IRoadmapService2Binder {

		@Override
		public String startRecording() {
			try {
				RecordSession newRec = new RecordSession(RoadmapService2.this);
				RoadmapService2.this._setCurRec(newRec);
				return newRec.getFullPath();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public String stopRecording() {
			RecordSession rec = RoadmapService2.this._setCurRec(null);
			if (rec == null)
				return null;
			return rec.getFullPath();
		}

		@Override
		public void setProperty(String key, String value) {
			if (key != null && value != null)
				RoadmapService2.this.mProperties.put(key, value);
		}

		@Override
		public String getProperty(String key) {
			return RoadmapService2.this.mProperties.get(key);
		}

		@Override
		public String currentRecording() {
			RecordSession rec = RoadmapService2.this.mCurRec;
			if (rec == null)
				return null;
			return rec.getFullPath();
		}

		@Override
		public void exit() {
			RoadmapService2.this._setCurRec(null);
			RoadmapService2.this.stopForeground(true);
		}

	}

	private RecordSession _setCurRec(final RecordSession newRec) {
		final RecordSession oldRec;
		synchronized (this) {
			oldRec = this.mCurRec;
			this.mCurRec = newRec;
		}
		if (oldRec != null) {
			oldRec.close();
		}
		if (newRec != null) {
			newRec.open();
		}
		return oldRec;
	}

	private void _startForeground() {
		int id = R.drawable.ic_launcher;
		String appName = this.getString(R.string.app_name);
		Notification notification = new Notification(id, appName, 0);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, RoadmapActivity2.class), 0);
		notification.setLatestEventInfo(this, appName, null, contentIntent);
		this.startForeground(id, notification);
	}

}
