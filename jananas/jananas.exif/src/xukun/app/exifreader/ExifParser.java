package xukun.app.exifreader;

import java.io.IOException;
import java.io.InputStream;

public interface ExifParser {

	void parse(InputStream is, ExifHandler h) throws IOException;
}
