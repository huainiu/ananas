package ananas.http_ui.kit;

public interface IHttpUIConfig {

	int getPortMin();

	int getPortMax();

	IHttpUIServlet getMainServlet();

}
