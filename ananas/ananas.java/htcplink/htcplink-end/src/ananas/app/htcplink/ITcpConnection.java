package ananas.app.htcplink;

import java.io.InputStream;
import java.io.OutputStream;

public interface ITcpConnection {

	InputStream getInputStream();

	OutputStream getOutputStream();

	int getPort();

	void close();

	String getHost();

}
