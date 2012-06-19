package ananas.app.htcplink.cpg;

public interface CpgService {

	void start();

	void stop();

	boolean isRunning();

	public static class Config {

		public Config makeCopy() {
			Config copy = new Config();
			return copy;
		}
	}

	public static class Factory {

		public CpgService createService(Config config) {
			return new ImplCpgService(config);
		}

	}

}
