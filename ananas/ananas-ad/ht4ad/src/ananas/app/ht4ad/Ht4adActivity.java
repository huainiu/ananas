package ananas.app.ht4ad;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class Ht4adActivity extends Activity {

	private WebView mWebViewBody;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.mWebViewBody = (WebView) this.findViewById(R.id.wv_body);

		this.mWebViewBody.loadData("hello,world", "text/html", "utf-8");

	}
}