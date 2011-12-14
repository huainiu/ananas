package jananas.json.impl.parser;

import jananas.json.JSONErrorInfo;
import jananas.json.JSONParser;
import jananas.json.impl.handler.JsonObjectBuilder;
import jananas.json.object.JSON_object;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


class JSONParserImpl implements JSONParser {

	private JSONErrorInfo mErrorInfo;

	@Override
	public JSON_object parse(String s) throws IOException {
		char[] chArray = s.toCharArray();
		Reader rdr = new MyCharArrayReader(chArray);
		return this.parse(rdr);
	}

	@Override
	public JSON_object parse(InputStream is) throws IOException {
		return this.parse(new InputStreamReader(is, "utf-8"));
	}

	@Override
	public JSON_object parse(Reader reader) throws IOException {
		JsonObjectBuilder h = JsonObjectBuilder.newInstance();
		SajParser parser = (new SajParserFactory()).createParser();
		parser.parse(reader, h);
		return h.getProduct();
	}

	@Override
	public JSON_object parse(byte[] ba) throws IOException {
		String str = new String(ba, "utf-8");
		return this.parse(str);
	}

	static class MyCharArrayReader extends Reader {

		private final char[] mArray;
		private int mPtr;
		private boolean mIsClosed;

		public MyCharArrayReader(char[] chArray) {
			this.mArray = chArray;
		}

		@Override
		public void close() throws IOException {
			this.mIsClosed = true;
			if (this.mIsClosed) {
			}
		}

		@Override
		public int read() throws IOException {
			if (this.mPtr < this.mArray.length) {
				return mArray[mPtr++];
			} else {
				return -1;
			}
		}

		@Override
		public int read(char[] cbuf, int off, int len) throws IOException {
			final int end = off + len;
			int i = off;
			for (; i < end; i++) {
				final int ch = this.read();
				if (ch < 0) {
					break;
				} else {
					cbuf[i] = (char) ch;
				}
			}
			return (i - off);
		}
	}

	@Override
	public JSONErrorInfo getErrorInfo() {
		return this.mErrorInfo;
	}
}
