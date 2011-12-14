package jananas.json.object;

/**
 * integer | float | boolean
 * */

public interface JSON_number extends JSON_object {

	int integerValue();

	float floatValue();

	double doubleValue();

	boolean booleanValue();
}
