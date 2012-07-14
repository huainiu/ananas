package jananas.json.object;

public interface JSON_dictionary extends JSON_object {

	void put(String key, JSON_object value);

	JSON_object get(String key);

	JSON_object delete(String key);

	String[] keys();

	int count();

}
