package jananas.json.impl;

import jananas.json.JSON;
import jananas.json.JSONObjectFactory;
import jananas.json.JSONParserFactory;
import jananas.json.JSONSerializerFactory;
import jananas.json.impl.parser.JSONParserFactoryImpl;

class JSON_xp_Module extends JSON {

	public static JSON newJSON() {
		return new JSON_xp_Module();
	}

	private final JSONSerializerFactory mSerializerFactory;
	private final JSONParserFactory mParserFactory;
	private final JSONObjectFactory mObjectFactory;

	public JSON_xp_Module() {

		this.mSerializerFactory = new JSONSerializerFactoryImpl();
		this.mParserFactory = new JSONParserFactoryImpl();
		this.mObjectFactory = new JSONObjectFactoryImpl();
	}

	@Override
	public JSONSerializerFactory getJSONSerializerFactory() {
		return this.mSerializerFactory;
	}

	@Override
	public JSONParserFactory getJSONParserFactory() {
		return this.mParserFactory;
	}

	@Override
	public JSONObjectFactory getJSONObjectFactory() {
		return this.mObjectFactory;
	}

}
