package ananas.roadmap;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;

public class RoadmapService2 extends Service {

	public static interface IRoadmapService2Binder {

		String startRecording(String basePath, int timeInterval,
				int distanceInterval);

		String stopRecording();

		String currentRecording();

		void setProperty(String key, String value);

		String getProperty(String key);

		void exit();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return this.mBinder;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	private final MyBinder mBinder = new MyBinder();

	private class MyBinder extends Binder implements IRoadmapService2Binder {

		@Override
		public String startRecording(String basePath, int timeInterval,
				int distanceInterval) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String stopRecording() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setProperty(String key, String value) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getProperty(String key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String currentRecording() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void exit() {
			// TODO Auto-generated method stub
			
		}

	}

}
