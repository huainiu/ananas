package jananas.json;

import jananas.json.object.JSON_object;

import java.io.OutputStream;
import java.io.Writer;



public interface JSONSerializer {

	void serialize(JSON_object obj, Writer writer);

	void serialize(JSON_object obj, OutputStream os);

	String serialize(JSON_object obj);

}
