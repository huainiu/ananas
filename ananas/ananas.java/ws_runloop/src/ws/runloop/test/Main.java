package ws.runloop.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import ws.runloop.RunLoop;
import ws.runloop.RunLoopAgent;
import ws.runloop.RunLoopTimer;

public class Main implements Runnable {

	boolean mIsClosed = false;

	RunLoop m_rlMain = null;
	RunLoop m_rlWorker = null;

	RunLoopTimer m_curTimer = null;

	public static void main(String[] arg) {
		System.out.println("welcome!");
		(new Main()).run();
	}

	@Override
	public void run() {
		(new Thread(new Runnable() {

			@Override
			public void run() {
				RunLoop runloop = RunLoopAgent.getManager().currentRunLoop();
				Main.this.m_rlWorker = runloop;
				while (!Main.this.mIsClosed) {
					runloop.run(10000);
				}
			}
		})).start();

		RunLoop runloop = RunLoopAgent.getManager().currentRunLoop();
		Main.this.m_rlMain = runloop;

		MyCmdRx runn = new MyCmdRx();
		runloop.startTimer(30, 30, runn);

		while (!Main.this.mIsClosed) {
			runloop.run(10000);
		}
	}

	private class MyCmdRx implements Runnable {

		final ByteArrayOutputStream mBaos = new ByteArrayOutputStream();

		public MyCmdRx() {
		}

		@Override
		public void run() {
			try {
				final InputStream is = System.in;
				for (;;) {
					if (0 >= is.available()) {
						break;
					}
					final int b = is.read();
					if (b == 0x0d || b == 0x0a) {
						final byte[] ba = mBaos.toByteArray();
						mBaos.reset();
						String line = new String(ba, "utf-8");
						this.onLine(line);
						break;
					} else {
						mBaos.write(b);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void onLine(String line) {

			if (line.equals("exit")) {
				System.out.println("exit this app!");
				Main.this.mIsClosed = true;

				Runnable nop = new Runnable() {
					@Override
					public void run() {
					}
				};
				Main.this.m_rlWorker.addTask(nop);
				Main.this.m_rlMain.addTask(nop);

			} else if (line.equals("kill_timer")) {
				System.out.println("do kill timer!");
				RunLoopTimer pold = Main.this.m_curTimer;
				Main.this.m_curTimer = null;
				if (pold != null) {
					pold.stop();
				}

			} else if (line.equals("start_timer")) {
				System.out.println("do start timer!");

				// Main.this.m_rlWorker.startTimer(15000, 5000, new
				// MyTimerTest());

				RunLoopTimer pnew = Main.this.m_rlWorker.startTimer(3300, 2400,
						new MyTimerTest());
				RunLoopTimer pold = Main.this.m_curTimer;
				Main.this.m_curTimer = pnew;
				if (pold != null) {
					pold.stop();
				}

			} else {
				System.out.println("unknow command:" + line);
			}
		}
	};

	class MyTimerTest implements Runnable {

		long lastTime = 0;
		int index = 0;

		public MyTimerTest() {
			this.run();
		}

		@Override
		public void run() {
			final long now = System.currentTimeMillis();
			final long dt = now - this.lastTime;
			final int i = (this.index++);
			final Thread thd = Thread.currentThread();

			this.lastTime = now;

			System.out.println(this + ".onTimer index:" + i + " delta:" + dt
					+ " thread:" + thd.getId());

		}
	}

}
