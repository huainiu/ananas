import jananas.json.JSON;
import jananas.json.JSONParser;
import jananas.json.JSONParserFactory;
import jananas.json.JSONSerializerFactory;
import jananas.json.object.JSON_object;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestMain {

	public static void main(String[] arg) {

		try {

			System.out.println("This is a test for JSON.");
			InputStream is = "".getClass()
					.getResourceAsStream("/test-json.txt");
			JSONParser parser = JSON.getInstance().getJSONParserFactory()
					.createParser();
			JSON_object obj = parser.parse(is);
			System.out.println(obj);

			PerformenceTest pt = new PerformenceTest();
			pt.testParser(obj);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static class PerformenceTest {

		public void testParser(JSON_object obj) throws IOException {

			OutputStream tmpOS = new OutputStream() {

				@Override
				public void write(int b) throws IOException {
					// do nothing
				}
			};

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JSONSerializerFactory jsf = JSON.getInstance()
					.getJSONSerializerFactory();
			jsf.createSerializer().serialize(obj, baos);
			byte[] ba = baos.toByteArray();

			JSONParserFactory jpf = JSON.getInstance().getJSONParserFactory();

			final long tsBegin = System.currentTimeMillis();
			final int totalLoop = (1000 * 1000);

			for (int iloop = totalLoop; iloop > 0; iloop--) {

				JSONParser parser = jpf.createParser();
				obj = parser.parse(ba);

				// System.out.println(obj);
				jsf.createSerializer().serialize(obj, tmpOS);

			}
			final long tsEnd = System.currentTimeMillis();

			final double timeCost = (tsEnd - tsBegin) / 1000.0;
			double lps = totalLoop / timeCost;

			System.out.println("run:" + totalLoop + " loops");
			System.out.println("cost:" + timeCost + "s");
			System.out.println("lps:" + lps + " loops/sec");

		}
	}

}
