package ananas.app.ht4ad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
import android.view.Window;
import android.webkit.WebView;

public class Ht4adActivity extends Activity {

	private WebView mWebViewBody;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		// build UI
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.mWebViewBody = (WebView) this.findViewById(R.id.wv_body);

		String page = this._loadStartPage("page/start.html");
		this.mWebViewBody.loadData(page, "text/html", "utf-8");

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

	private String _loadStartPage(String path) {
		try {
			InputStream is = this.getAssets().open(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] ba = new byte[256];
			for (int cb = is.read(ba); cb > 0; cb = is.read(ba)) {
				baos.write(ba);
			}
			ba = baos.toByteArray();
			is.close();
			baos.close();
			return new String(ba);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "no file:" + path;
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
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private class MyConnection implements ServiceConnection {

		private IHt4adBinder mBinder;

		public void onServiceConnected(ComponentName arg0, IBinder binder) {
			if (binder instanceof IHt4adBinder) {
				this.mBinder = (IHt4adBinder) binder;
				this.mBinder.hello("bill");
			}
		}

		public void onServiceDisconnected(ComponentName arg0) {
			this.mBinder = null;
		}

		public IHt4adBinder getBinder() {
			return this.mBinder;
		}

	}

	private final MyConnection mServiceConn = new MyConnection();

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

}
