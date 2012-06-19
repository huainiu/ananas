package ananas.app.htcplink;

import java.io.InputStream;

public class Main {

	public static void main(String[] args) {

		System.out.println(Main.class + ".begin");

		CmdLineArgs cla = CmdLineArgs.Factory.newInstance(args);

		for (String key : cla.keys()) {
			String value = cla.getValue(key);
			System.out.println("arg " + key + " : " + value);
		}

		EndCore endCore = EndCore.Factory.newInstance(cla);
		endCore.start();

		try {
			InputStream is = System.in;
			for (int ch = is.read(); ch >= 0; ch = is.read()) {
				if (ch == 'Q')
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		endCore.stop();

		System.out.println(Main.class + ".end");

	}
}
