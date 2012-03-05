package ws.runloop;

import java.util.Hashtable;

class RunLoopImpl {

	private static final RunLoopImpl sImpl = new RunLoopImpl();
	//
	private final RunLoopRegistrar mRegistrar = new RunLoopRegistrar();

	public static RunLoopManager getManager() {
		return sImpl.mManager;
	}

	private final RunLoopManager mManager = new RunLoopManager() {

		@Override
		public RunLoop currentRunLoop() {
			final Thread thd = Thread.currentThread();
			MyRunLoop runloop = RunLoopImpl.this.mRegistrar
					.getRunLoopByThread(thd);
			if (runloop == null) {
				runloop = new MyRunLoop(thd);
				RunLoopImpl.this.mRegistrar.registerRunLoop(runloop);
			}
			return runloop;
		}
	};

	class RunLoopRegistrar {

		final Hashtable<Thread, MyRunLoop> mTable = new Hashtable<Thread, MyRunLoop>();

		int mCleanupCounter = 1;

		public MyRunLoop getRunLoopByThread(Thread thd) {
			return this.mTable.get(thd);
		}

		public void registerRunLoop(MyRunLoop runloop) {
			this.doCleanup();
			this.mTable.put(runloop.mThread, runloop);
		}

		private void doCleanup() {
			final int cnt = (this.mCleanupCounter++);
			if ((cnt % 5) != 0) {
				return;
			}
			Thread[] array = this.mTable.keySet().toArray(new Thread[0]);
			for (Thread thd : array) {
				if (thd.getState() == Thread.State.TERMINATED) {
					this.mTable.remove(thd);
				}
			}
		}
	}

	class MyTaskFIFO {

	}

	class MyRunLoop implements RunLoop {

		private final Thread mThread;
		private final MyTaskFIFO mFIFO = new MyTaskFIFO();

		public MyRunLoop(Thread thd) {
			this.mThread = thd;
		}

		@Override
		public void run(long ms) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addTask(Runnable task) {
			// TODO Auto-generated method stub

		}

		@Override
		public RunLoopTimer startTimer(long delay, long interval, Runnable task) {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
