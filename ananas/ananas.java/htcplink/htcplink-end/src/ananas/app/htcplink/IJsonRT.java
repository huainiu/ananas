package ananas.app.htcplink;

public interface IJsonRT {

	interface Listener {

		void onReceive(String jsonString);

	}

	void setListener(Listener listener);

	void send(String jsonString);

}
