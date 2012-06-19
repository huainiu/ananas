package ananas.app.htcplink;

public interface ITcpConnectionFactory {

	ITcpConnection open(String host, int port);
}
