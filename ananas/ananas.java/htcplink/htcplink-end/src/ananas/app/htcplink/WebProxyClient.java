package ananas.app.htcplink;

public interface WebProxyClient {

	void connect();

	void disconnect();

	boolean isConnected();

	void sendPackage(Package pkg);

	public class ClientType {

		public static final ClientType SPG = new ClientType();
		public static final ClientType CPG = new ClientType();

	}

	public class Config {

		String url;
		int channel;
		ClientType clientType;
	}

	public class Package {
	}

	public static class Factory {

		public WebProxyClient createClient(Config config) {
			return new ImplWebProxyClient(config);
		}

	}

}
