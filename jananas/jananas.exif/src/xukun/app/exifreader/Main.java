package xukun.app.exifreader;

import java.io.InputStream;

public class Main {

	public static void main(String[] args) {
		String name = Main.class.getName();
		System.out.println(name + " - begin");
		try {
			InputStream is = "".getClass().getResourceAsStream("/test.jpg");
			ExifParser parser = ExifParserFactory.getDefault().createParser();
			ExifHandler h = new DefaultExifHandler();
			parser.parse(is, h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(name + " - end");
	}

}
