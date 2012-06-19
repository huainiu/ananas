package ananas.app.htcplink;

public interface HttpClient {

	void connect();

	void disconnect();

	class Config {

		Config makeCopy() {
			return new Config();
		}
	}

	class Factory {

		public static HttpClient newInstance(Config config) {
			return new MyImpl(config);
		}
	}

	// implements
	static class MyImpl implements HttpClient, Runnable {

		private final Config mConfig;
		private Thread mThread;

		MyImpl(Config config) {
			this.mConfig = config.makeCopy();
		}

		@Override
		public void connect() {

			if (this.mThread == null) {
				Thread thd = new Thread(this);
				this.mThread = thd;
				thd.start();
			}
		}

		@Override
		public void disconnect() {

			if (this.mThread != null) {
				this.mThread = null;
			}
		}

		@Override
		public void run() {

			Thread thd = Thread.currentThread();

			if (thd.equals(this.mThread)) {
				
				
			}

		}

	}

}
