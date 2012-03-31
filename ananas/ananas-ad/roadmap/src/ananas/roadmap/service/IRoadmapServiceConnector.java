package ananas.roadmap.service;

public interface IRoadmapServiceConnector {

	interface ConnectionListener {

		void onConnected(IRoadmapServiceConnector conn);

		void onDisconnected(IRoadmapServiceConnector conn);
	}

	void setConnectionListener(ConnectionListener l);

	void connect();

	void disconnect();

	IRoadmapServiceBinderEx getBinderEx();
}
