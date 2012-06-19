package ananas.app.htcplink;

public class Main {

	public static void main(String[] args) {

		System.out.println(Main.class + ".begin");

		CmdLineArgs cla = CmdLineArgs.Factory.newInstance(args);

		for (String key : cla.keys()) {
			String value = cla.getValue(key);
			System.out.println("arg " + key + ":" + value);
		}

		System.out.println(Main.class + ".end");

	}
}
