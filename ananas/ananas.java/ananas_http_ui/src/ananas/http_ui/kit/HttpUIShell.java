package ananas.http_ui.kit;

import java.io.InputStream;
import java.io.OutputStream;

public class HttpUIShell implements Runnable, IHttpUIShell {

	private final IHttpUIConfig mConfig;

	public HttpUIShell(IHttpUIConfig config) {
		this.mConfig = config;
	}

	@Override
	public void run() {
		HttpUIService service = new HttpUIService(this.mConfig);
		service.start();
		this._waitUntilStarted(service);
		if (service.getStatus().getPhase() == IHttpUIStatus.phase_running) {

			try {
				int port = service.getStatus().getPort();
				String url = "http://127.0.0.1:" + port + "/index.html";
				System.out.println("open url " + url);
				String cmd = "explorer " + url;
				Process proc = Runtime.getRuntime().exec(cmd);
				proc.hashCode();
				InputStream is = System.in;
				OutputStream os = System.out;
				IOStreamPump pump = new IOStreamPump(is, os);
				pump.run();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		service.stop();
	}

	private void _waitUntilStarted(HttpUIService service) {

		int step = 100;
		for (int timeout = 5000; timeout > 0; timeout -= step) {
			int phase = service.getStatus().getPhase();
			if (phase == IHttpUIStatus.phase_running) {
				return;
			} else {
				try {
					Thread.sleep(step);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
