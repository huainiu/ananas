package jananas.json;

import jananas.json.object.JSON_object;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;



public interface JSONParser {

	JSON_object parse(String s) throws IOException;

	JSON_object parse(InputStream is) throws IOException;

	JSON_object parse(Reader reader) throws IOException;

	JSON_object parse(byte[] ba) throws IOException;

	JSONErrorInfo getErrorInfo();

}
