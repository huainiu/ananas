package ananas.http_ui.test;

import ananas.http_ui.kit.HttpUIConfig;
import ananas.http_ui.kit.HttpUIShell;
import ananas.http_ui.kit.IHttpUIConfig;

public class Main {

	public static void main(String[] arg) {
		System.out.println(Main.class + ".onBegin");
		IHttpUIConfig config = new HttpUIConfig();
		(new HttpUIShell(config)).run();
		System.out.println(Main.class + ".onEnd");
	}
}
