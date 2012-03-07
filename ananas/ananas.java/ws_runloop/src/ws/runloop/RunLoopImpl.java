package ws.runloop;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

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
			this.mTable.put(runloop.getThread(), runloop);
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

		private final MySyncObject mSyncObject;

		private final LinkedList<Runnable> mList = new LinkedList<Runnable>();

		public MyTaskFIFO(MySyncObject syncObject) {
			this.mSyncObject = syncObject;
		}

		public void push(Runnable task) {
			if (task != null) {
				this._doTaskIO(task);
				this.mSyncObject.doWakeup();
			}
		}

		public Runnable pop() {
			return this._doTaskIO(null);
		}

		private synchronized Runnable _doTaskIO(Runnable runn) {
			if (runn == null) {
				return this.mList.pollFirst();
			} else {
				this.mList.addLast(runn);
				return null;
			}
		}

	}

	class MySyncObject {

		public void doSleep(long ms) {
			try {
				if (ms < 1)
					ms = 1;
				// System.out.println(this + ".doSleep:" + ms);
				synchronized (this) {
					this.wait(ms);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void doWakeup() {
			try {
				synchronized (this) {
					this.notifyAll();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class MyThreadBind {

		private final Thread mThread;

		public MyThreadBind(Thread thd) {
			this.mThread = thd;
			this.checkThread();
		}

		public void checkThread() {
			Thread thd = Thread.currentThread();
			if (thd.getId() != this.mThread.getId()) {
				throw new RuntimeException("different thread!");
			}
		}

		public Thread getThread() {
			return this.mThread;
		}
	}

	class MyRunLoop extends MyThreadBind implements RunLoop {

		private final MyTaskFIFO mFIFO;
		private final MySyncObject mSyncObject = new MySyncObject();
		private final MyTimerManager mTimerMngr;

		public MyRunLoop(Thread thd) {
			super(thd);
			this.checkThread();
			this.mFIFO = new MyTaskFIFO(this.mSyncObject);
			this.mTimerMngr = new MyTimerManager(thd);
		}

		@Override
		public void run(long timeout) {
			this.checkThread();
			timeout = (timeout > 0) ? timeout : 0;
			final long now = System.currentTimeMillis();
			final long ms1 = this.mTimerMngr.fireAndGetNextInterval(now);
			final Runnable runn = this.mFIFO.pop();
			if (runn != null) {
				this._safe_exe(runn);
				return;
			} else {
				this.mSyncObject.doSleep((ms1 < timeout) ? ms1 : timeout);
			}
		}

		private void _safe_exe(Runnable runn) {
			try {
				runn.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void addTask(Runnable task) {
			if (task == null)
				return;
			this.mFIFO.push(task);
		}

		@Override
		public RunLoopTimer startTimer(long delay, long interval, Runnable task) {
			if (delay < 0)
				delay = 0;
			if (interval < 0)
				interval = 0;
			if (task == null)
				return null;
			MyTimer timer = new MyTimer(this, delay, interval, task);
			this.addTask(timer);
			return timer;
		}
	}

	class MyTimerManager extends MyThreadBind {

		private final Vector<MyTimer> mList = new Vector<MyTimer>();

		public MyTimerManager(Thread thd) {
			super(thd);
		}

		public long fireAndGetNextInterval(long now) {
			this.checkThread();
			long nextIV = 3600 * 1000;
			final MyTimer[] array = this.mList.toArray(new MyTimer[0]);
			for (MyTimer timer : array) {
				final long niv = timer.fireAndGetNextInterval(now);
				if (niv < nextIV && niv >= 0) {
					nextIV = niv;
				}
				if (timer.isStopped()) {
					this.mList.remove(timer);
				}
			}
			return nextIV;
		}

		public void addTimer(MyTimer timer) {
			this.checkThread();
			this.mList.addElement(timer);
		}
	}

	class MyTimer implements RunLoopTimer, Runnable {

		private final MyRunLoop mOwnerRunLoop;
		private final Runnable mTask;
		private final long mInterval;
		private final long mDelay;
		//
		private boolean mIsStopped = false;
		private boolean mIsStarted = false;
		private long mNextFireTime;

		public MyTimer(MyRunLoop runloop, long delay, long interval,
				Runnable task) {

			this.mTask = task;
			this.mDelay = delay;
			this.mInterval = interval;
			this.mOwnerRunLoop = runloop;

			this.mNextFireTime = System.currentTimeMillis() + this.mDelay;
		}

		public long fireAndGetNextInterval(long now) {
			if (this.mIsStopped) {
				return (3600 * 1000);
			}
			if (this.mNextFireTime <= now) {
				// fire
				this._fire();
				if (this.mInterval > 0) {
					this.mNextFireTime = now + this.mInterval;
					return this.mInterval;
				} else {
					this.mIsStopped = true;
					return (3600 * 1000);
				}
			} else {
				return (this.mNextFireTime - now);
			}
		}

		private void _fire() {
			try {
				this.mTask.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public boolean isStopped() {
			return this.mIsStopped;
		}

		@Override
		public void stop() {
			this.mIsStopped = true;
		}

		@Override
		public void run() {
			// load self
			if (this.mIsStarted) {
				throw new RuntimeException("already started");
			} else {
				this.mIsStarted = true;
			}
			this.mOwnerRunLoop.mTimerMngr.addTimer(this);
		}
	}

}
