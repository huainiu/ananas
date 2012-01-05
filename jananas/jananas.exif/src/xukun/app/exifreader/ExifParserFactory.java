package xukun.app.exifreader;

import java.io.IOException;
import java.io.InputStream;

public abstract class ExifParserFactory {

	public abstract ExifParser createParser();

	public static ExifParserFactory getDefault() {
		return new ExifParserFactoryImpl();
	}

	/**
	 * implements
	 * */

	private static final class ExifParserFactoryImpl extends ExifParserFactory {

		@Override
		public ExifParser createParser() {
			return new ExifParserImpl();
		}
	}

	private static final class ExifParserImpl implements ExifParser {

		@Override
		public void parse(InputStream is, ExifHandler h) throws IOException {

			for (int i = 0;; i++) {
				final int b = is.read();
				if (b < 0) {
					break;
				}

				if (i < 10) {
					String s = Integer.toString(b, 16);
					System.out.println("b[" + i + "] = 0x" + s);
				}

			}

		}

	}
}
