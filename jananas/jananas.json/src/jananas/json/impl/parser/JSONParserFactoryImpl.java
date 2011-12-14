package jananas.json.impl.parser;

import jananas.json.JSONParser;
import jananas.json.JSONParserFactory;

public class JSONParserFactoryImpl implements JSONParserFactory {

	@Override
	public JSONParser createParser() {
		return new JSONParserImpl();
	}

}
