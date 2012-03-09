package ananas.http_ui.kit;

public interface IHttpUIStatus {

	final static int phase_init = 0;
	final static int phase_starting = 1;
	final static int phase_running = 2;
	final static int phase_stopped = 3;
	final static int phase_error = 4;

	int getPort();

	int getPhase();
}
