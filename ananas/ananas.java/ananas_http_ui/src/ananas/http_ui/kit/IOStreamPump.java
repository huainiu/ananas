package ananas.http_ui.kit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOStreamPump implements Runnable {

	private final OutputStream mOS;
	private final InputStream mIS;

	public IOStreamPump(InputStream is, OutputStream os) {
		this.mIS = is;
		this.mOS = os;
	}

	@Override
	public void run() {
		InputStream is = mIS;
		OutputStream os = mOS;
		try {
			for (int b = is.read(); b >= 0; b = is.read()) {
				os.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
