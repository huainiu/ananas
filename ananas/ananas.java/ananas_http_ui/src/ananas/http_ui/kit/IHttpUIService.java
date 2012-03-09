package ananas.http_ui.kit;

public interface IHttpUIService {

	IHttpUIConfig getConfig();

	IHttpUIStatus getStatus();

	void start();

	void stop();
}
