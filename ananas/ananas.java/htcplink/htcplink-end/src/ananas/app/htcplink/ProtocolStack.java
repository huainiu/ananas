package ananas.app.htcplink;

public interface ProtocolStack {

	ElementsFactory getElementsFactory();

	Config getConfig();

	interface Config {
	}

	interface ElementsFactory {
	}

	class Factory {

		ProtocolStack newProtocolStack(Config config) {
			return null;
		}

	}

}
