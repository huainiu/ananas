import jananas.json.JSON;
import jananas.json.JSONParser;
import jananas.json.object.JSON_object;

import java.io.IOException;
import java.io.InputStream;


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

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
