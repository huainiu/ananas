package ws.runloop;

public interface RunLoop {

	void run(long ms);

	void addTask(Runnable task);

	RunLoopTimer startTimer(long delay, long interval, Runnable task);

}
