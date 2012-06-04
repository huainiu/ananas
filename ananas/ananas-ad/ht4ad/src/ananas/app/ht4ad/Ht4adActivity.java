package ananas.app.ht4ad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

		String page = this._loadStartPage("page/start.html");
		this.mWebViewBody.loadData(page, "text/html", "utf-8");

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
}
