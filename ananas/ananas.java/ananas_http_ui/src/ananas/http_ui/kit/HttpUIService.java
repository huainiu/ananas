package ananas.http_ui.kit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

public class HttpUIService implements IHttpUIService {

	private final IHttpUIConfig mConfig;

	public HttpUIService(IHttpUIConfig config) {
		this.mConfig = config;
	}

	@Override
	public IHttpUIConfig getConfig() {
		return this.mConfig;
	}

	@Override
	public void start() {
		if (this.mRunnAccept == null) {
			this.mRunnAccept = new MyRunnAccept();
			(new Thread(this.mRunnAccept)).start();
		}
	}

	@Override
	public void stop() {
		if (this.mRunnAccept != null) {
			this.mRunnAccept.close();
		}
	}

	@Override
	public IHttpUIStatus getStatus() {
		return this.mStatus;
	}

	private class MyStatus implements IHttpUIStatus {

		private int mPort = 0;
		private int mPhase = 0;

		@Override
		public int getPort() {
			return this.mPort;
		}

		@Override
		public int getPhase() {
			return this.mPhase;
		}
	}

	private final MyStatus mStatus = new MyStatus();
	private MyRunnAccept mRunnAccept;

	class MyRunnAccept implements Runnable {

		private ServerSocket mAcceptSocket;

		@Override
		public void run() {

			try {
				final MyStatus status = HttpUIService.this.mStatus;
				final IHttpUIConfig config = HttpUIService.this.mConfig;
				status.mPhase = IHttpUIStatus.phase_starting;
				ServerSocket sock0 = null;
				for (int port = config.getPortMin(); port < config.getPortMax(); port++) {
					sock0 = this._createAcceptSocket(port);
					if (sock0 != null)
						break;
				}
				if (sock0 == null) {
					status.mPhase = IHttpUIStatus.phase_error;
					System.err.println("cannot bind to ports.");
					return;
				} else {
					status.mPort = sock0.getLocalPort();
					status.mPhase = IHttpUIStatus.phase_running;
					this.mAcceptSocket = sock0;
					System.out
							.println("listen at port:" + sock0.getLocalPort());
				}

				for (;;) {
					Socket sock1 = sock0.accept();
					MySession session = new MySession(sock1);
					(new Thread(session)).start();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// status.mPhase = IHttpUIStatus.phase_stopped;

		}

		private ServerSocket _createAcceptSocket(int port) {
			ServerSocket sock = null;
			try {
				sock = ServerSocketFactory.getDefault().createServerSocket();
				InetSocketAddress addr = new InetSocketAddress(port);
				sock.bind(addr);
				return sock;
			} catch (Exception e) {
				// e.printStackTrace();
			}
			if (sock != null) {
				try {
					sock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		public void close() {
			ServerSocket sock = this.mAcceptSocket;
			if (sock != null) {
				try {
					sock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

	class MySession implements Runnable {

		private final Socket mSocket;

		public MySession(Socket socket) {
			this.mSocket = socket;
		}

		@Override
		public void run() {
			try {
				System.out.println("new session");
				InputStream is = this.mSocket.getInputStream();
				OutputStream os = this.mSocket.getOutputStream();
				is.hashCode();
				os.hashCode();
				IHttpUIConfig config = HttpUIService.this.mConfig;
				IHttpUIResponse response = null;
				IHttpUIRequest request = null;
				config.getMainServlet().process(request, response);

			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				this.mSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
