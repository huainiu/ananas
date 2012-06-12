package ananas.app.ht4ad;

import ananas.app.ht4ad.jsapi2.IJavascriptAPI2;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Ht4adActivity extends Activity {

	private final static String sInitURL = "http://m.qq.com/";

	private WebView mWebViewBody;
	private boolean mPageLoaded = false;
	private final MyConnection mServiceConn = new MyConnection();
	private IJavascriptAPI2 mJsAPI;
	private ProgressBar mProgNetWorking;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		// build UI
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.mWebViewBody = (WebView) this.findViewById(R.id.wv_body);
		String api_name = IJavascriptAPI2.api_name;
		IJavascriptAPI2 api = this.getJsapi();
		this.mWebViewBody.addJavascriptInterface(api, api_name);
		this.mWebViewBody.getSettings().setJavaScriptEnabled(true);
		this.mWebViewBody.setWebChromeClient(new MyWebChromeClient());
		this.mWebViewBody.setWebViewClient(new MyWebViewClient());

		this.mProgNetWorking = (ProgressBar) this
				.findViewById(R.id.progressNetWorking);
		this.mProgNetWorking.setVisibility(View.INVISIBLE);

		// start service
		Intent service = new Intent(this, Ht4adService.class);
		this.startService(service);

	}

	@Override
	protected void onPause() {
		this.unbindService(this.mServiceConn);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent service = new Intent(this, Ht4adService.class);
		this.bindService(service, this.mServiceConn, Context.BIND_AUTO_CREATE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.itemExit: {
			this._showExitAppDialog();
			break;
		}
		case R.id.itemHome: {
			this._goHome();
			break;
		}
		case R.id.itemHelp: {
			this._goHelp();
			break;
		}
		case R.id.itemRefresh: {
			this.mWebViewBody.reload();
			break;
		}
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private class MyConnection implements ServiceConnection, IHt4adBinder {

		private IHt4adBinder mTarget;

		public void onServiceConnected(ComponentName arg0, IBinder binder) {
			if (binder instanceof IHt4adBinder) {
				this.mTarget = (IHt4adBinder) binder;
				this.mTarget.hello("world");
				Ht4adActivity.this._goInit();
			}
		}

		public void onServiceDisconnected(ComponentName arg0) {
			this.mTarget = null;
		}

		public IHt4adBinder getBinder() {
			return this;
		}

		public String get(String key) {
			return this.mTarget.get(key);
		}

		public void set(String key, String value) {
			this.mTarget.set(key, value);
		}

		public String[] listKeys() {
			return this.mTarget.listKeys();
		}

		public void hello(String str) {
			this.mTarget.hello(str);
		}

		public void stopService() {
			this.mTarget.stopService();
		}

	}

	private void _showExitAppDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// MyActivity.this.finish();
								Ht4adActivity.this._exitApp();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}

	public void _goInit() {
		if (!this.mPageLoaded) {
			this.mPageLoaded = true;
			this.mWebViewBody.loadUrl(sInitURL);
		}
	}

	public void _goHome() {
		String url = this.getJsapi().get(IJavascriptAPI2.memory_home_url);
		this.mWebViewBody.loadUrl(url);
	}

	public void _goHelp() {
		String url = this.getJsapi().get(IJavascriptAPI2.memory_help_url);
		this.mWebViewBody.loadUrl(url);
	}

	protected void _exitApp() {

		IHt4adBinder binder = this.getBinder();
		binder.stopService();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}

	private IHt4adBinder getBinder() {
		return this.mServiceConn.getBinder();
	}

	public IJavascriptAPI2 getJsapi() {
		IJavascriptAPI2 api = this.mJsAPI;
		if (api == null) {
			Ht4adActivityJsapiTools tools = new Ht4adActivityJsapiTools(this);
			IHt4adBinder binder = this.getBinder();
			tools.setNext(binder);
			this.mJsAPI = api = tools;
		}
		return api;
	}

	class MyWebChromeClient extends WebChromeClient {

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
			if ((0 <= newProgress) && (newProgress < 100)) {
				Ht4adActivity.this.mProgNetWorking.setVisibility(View.VISIBLE);
			} else {
				Ht4adActivity.this.mProgNetWorking
						.setVisibility(View.INVISIBLE);
			}
		}
	}

	class MyWebViewClient extends WebViewClient {
	}

}
