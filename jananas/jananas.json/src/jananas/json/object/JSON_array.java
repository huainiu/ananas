package jananas.json.object;

public interface JSON_array extends JSON_object {

	void add(JSON_object item);

	JSON_object get(int index);

	JSON_object delete(int index);

	JSON_object[] items();

	int count();

}
