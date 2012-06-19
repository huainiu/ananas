package ananas.app.htcplink.cpg;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.tomcat.util.net.ServerSocketFactory;

public class ImplCpgService implements CpgService, Runnable {

	private final Config mConfig;
	private Thread mThread;
	private boolean mHasStarted = false;
	private boolean mHasStopped = false;
	private boolean mDoStop = false;

	public ImplCpgService(Config config) {
		this.mConfig = config.makeCopy();
	}

	@Override
	public void start() {
		if (this.mThread == null) {
			this.mThread = new Thread(this);
			this.mThread.start();
		}
	}

	@Override
	public void stop() {
		if (this.mThread != null) {
			this.mDoStop = true;
		}
	}

	@Override
	public boolean isRunning() {
		return (this.mHasStarted && (!this.mHasStopped));
	}

	@Override
	public void run() {
		this.mHasStarted = true;
		try {
			int port = 0;
			String host = "";
			InetSocketAddress ep = new InetSocketAddress(host, port);
			ServerSocketFactory ssf = ServerSocketFactory.getDefault();
			ServerSocket ssock = ssf.createSocket(port);
			ssock.bind(ep);
			for (;;) {
				Socket sessionSock = ssock.accept();
				Session session = new Session(sessionSock);
				Thread thd = new Thread(session);
				thd.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.mHasStopped = true;
	}

	private class Session implements Runnable {

		private final Socket mSocket;

		public Session(Socket socket) {
			this.mSocket = socket;
		}

		@Override
		public void run() {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
