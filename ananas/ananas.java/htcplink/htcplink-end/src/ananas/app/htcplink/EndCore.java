package ananas.app.htcplink;

public interface EndCore {

	void start();

	void stop();

	boolean isRunning();

	class Factory {

		public static EndCore newInstance(CmdLineArgs args) {
			return MyImpl.newInst(args);
		}

	}

	static class MyImpl implements EndCore {

		public static EndCore newInst(CmdLineArgs args) {

			return new MyImpl();

		}

		@Override
		public void start() {

			HttpClient.Config config = new HttpClient.Config();
			HttpClient hc = HttpClient.Factory.newInstance(config);
			hc.connect();

		}

		@Override
		public void stop() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isRunning() {
			// TODO Auto-generated method stub
			return false;
		}
	}

}
