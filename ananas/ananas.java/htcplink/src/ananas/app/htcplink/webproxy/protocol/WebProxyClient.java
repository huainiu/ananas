package ananas.app.htcplink.webproxy.protocol;

public interface WebProxyClient {

	public class Config {
	}

	public static class Factory {

		public WebProxyClient createClient(Config config) {
			return new ImplWebProxyClient(config);
		}

	}

}
