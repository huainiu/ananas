package jananas.json.impl.handler;

import jananas.json.object.JSON_object;

public abstract class JsonObjectBuilder implements SajHandler {

	public abstract JSON_object getProduct();

	public static JsonObjectBuilder newInstance() {
		return new JsonObjectBuilderImpl();
	}

}
