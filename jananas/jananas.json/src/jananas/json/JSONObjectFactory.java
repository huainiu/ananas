package jananas.json;

import jananas.json.object.JSON_array;
import jananas.json.object.JSON_dictionary;
import jananas.json.object.JSON_null;
import jananas.json.object.JSON_number;
import jananas.json.object.JSON_string;

public interface JSONObjectFactory {

	JSON_number booleanValue(boolean b);

	JSON_number intValue(int n);

	JSON_number doubleValue(double n);

	JSON_null nullValue();

	JSON_string string(String s);

	JSON_array array();

	JSON_dictionary dictionary();

}
