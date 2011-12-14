package jananas.json.impl.parser;

import jananas.json.impl.handler.SajHandler;

import java.io.IOException;
import java.io.Reader;




public interface SajParser {

	void parse(Reader reader, SajHandler h) throws IOException;

}
