package ananas.app.ht4ad;

import ananas.app.ht4ad.jsapi2.IJavascriptAPI2;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Ht4adService extends Service {

	private final MyBinder mBinder = new MyBinder();
	private IJavascriptAPI2 mJsAPI;

	@Override
	public IBinder onBind(Intent arg0) {
		return this.mBinder;
	}

	private class MyBinder extends Binder implements IHt4adBinder {

		public void hello(String str) {
			System.out.println(this + ".hello():" + str);
		}

		public void stopService() {
			Ht4adService.this._stopForeground();
		}

		public String get(String key) {
			IJavascriptAPI2 api = Ht4adService.this.getJsAPI();
			return api.get(key);
		}

		public void set(String key, String value) {
			IJavascriptAPI2 api = Ht4adService.this.getJsAPI();
			api.set(key, value);
		}

		public String[] listKeys() {
			IJavascriptAPI2 api = Ht4adService.this.getJsAPI();
			return api.listKeys();
		}
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
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		this._startForeground();
	}

	private void _startForeground() {
		System.out.println(this + "._startForeground()");
		int id = R.drawable.ic_launcher;
		String appName = this.getString(R.string.app_name);
		Notification notification = new Notification(id, appName, 0);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, Ht4adActivity.class), 0);
		notification.setLatestEventInfo(this, appName, null, contentIntent);
		this.startForeground(id, notification);
	}

	private void _stopForeground() {
		this.stopForeground(true);
	}

	public IJavascriptAPI2 getJsAPI() {
		IJavascriptAPI2 api = this.mJsAPI;
		if (api == null) {
			Ht4adServiceJsapiTools tools = new Ht4adServiceJsapiTools(this);
			this.mJsAPI = api = tools;
		}
		return api;
	}

}
