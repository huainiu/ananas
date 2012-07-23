package ananas.app.passworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class PassWorldActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button btn = (Button) this.findViewById(R.id.buttonGotoFuyoo);
		final OnClickListener listenOnClick = new OnClickListener() {

			public void onClick(View arg0) {

				PassWorldActivity.this.openZxing();
			}
		};
		btn.setOnClickListener(listenOnClick);

		{
			Intent intent = this.getIntent();
			String dat = intent.getDataString();

			TextView tv = (TextView) this.findViewById(R.id.textViewTitle);
			tv.setText(dat);
		}

		{
			WebView webview = (WebView) this.findViewById(R.id.webview);
			webview.loadUrl("content://ananas.passworld.providers.httpprovider/index.html");
		}

	}

	private void openZxing() {

		try {

			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("zxing://scan/"));
			PassWorldActivity.this.startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();

			AlertDialog.Builder builder = new Builder(this);
			builder.setMessage(e.toString());
			builder.setTitle("Exception");

			builder.create().show();

		}

	}
}
