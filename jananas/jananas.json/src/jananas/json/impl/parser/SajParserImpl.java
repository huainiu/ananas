package jananas.json.impl.parser;

import jananas.json.JSONException;
import jananas.json.impl.handler.SajHandler;
import jananas.json.impl.handler.SajLocator;
import jananas.json.impl.handler.SajLocator_internal;

import java.io.IOException;
import java.io.Reader;


class SajParserImpl implements SajParser {

	static final int TYPE_DICT = 1;
	static final int TYPE_ARRAY = 2;
	static final int TYPE_STRING = 4;
	static final int TYPE_BOOLEAN = 5;
	static final int TYPE_NULL = 6;
	static final int TYPE_NUMBER = 7;

	@Override
	public void parse(Reader reader, SajHandler h) throws IOException {

		try {
			MyCore core = new MyCore(reader);
			core.doParse(h);
		} catch (JSONException e) {
			// e.printStackTrace();
			h.onException(e);
		}

	}

	private static class MyCore {

		private final Reader mReader2;
		private final SajLocator mLocator;
		private int mCurChar;

		public MyCore(Reader reader) {

			final MyLocatorReaderWrapper locator = new MyLocatorReaderWrapper(
					reader);

			this.mLocator = locator;
			this.mReader2 = locator;

		}

		public void doParse(final SajHandler h) throws IOException {
			this._read();
			h.setLocator(mLocator);
			h.onDocBegin();
			this._SkipSpace();
			if (!this._isEOF()) {
				this.doSkipTestReadElement(h);
			}
			h.onDocEnd();
		}

		private boolean _isEOF() {
			return (this.mCurChar < 0);
		}

		public void doSkipTestReadElement(final SajHandler h)
				throws IOException {

			this._SkipSpace();
			final int type = this._TestElementType();
			this.doReadElement(h, type);
		}

		private void doReadElement(final SajHandler h, int type)
				throws IOException {

			switch (type) {
			case TYPE_ARRAY: {
				this.doReadArray(h);
				return;
			}
			case TYPE_NUMBER: {
				this.doReadNumber(h);
				return;
			}
			case TYPE_STRING: {
				this.doReadString(h);
				return;
			}
			case TYPE_DICT: {
				this.doReadDict(h);
				return;
			}
			case TYPE_BOOLEAN: {
				this.doReadBoolean(h);
				return;
			}
			case TYPE_NULL: {
				this.doReadNull(h);
				return;
			}
			default:
				throw new JSONException("unknow json type:" + type);
			}
		}

		private void doReadNull(final SajHandler h) throws IOException {
			this._readAndCheck("null");
			h.onNull();
		}

		private void _readAndCheck(String string) throws IOException {

			int len = string.length();
			char[] buf = new char[len];
			int cnt = this._read(buf);
			if (cnt == len) {
				String s2 = new String(buf);
				if (string.equals(s2)) {
					return;
				}
			}
			throw new JSONException("It maybe need a '" + string + "' here.");
		}

		private int _read(char[] buf) throws IOException {

			int i = 0;
			for (; i < buf.length; i++) {
				int ch = this._read();
				if (ch >= 0) {
					buf[i] = (char) ch;
				} else {
					break;
				}
			}
			return i;
		}

		private void doReadBoolean(final SajHandler h) throws IOException {

			int ch = this._getChar();
			switch (ch) {
			case 't': {
				this._readAndCheck("true");
				h.onBoolean(true);
				return;
			}
			case 'f': {
				this._readAndCheck("false");
				h.onBoolean(false);
				return;
			}
			}

		}

		private int _getChar() throws IOException {
			return this.mCurChar;
		}

		private void doReadDict(final SajHandler h) throws IOException {

			this._readAndCheck("{");
			h.onDictBegin();

			for (int i = 0;; i++) {

				this._SkipSpace();
				if (i == 0) {
					int ch = this._getChar();
					if (ch == '}') {
						break;
					}
				}
				String key = this._readString();
				h.onDictKey(key);
				this._SkipSpace();
				this._readAndCheck(":");

				this.doSkipTestReadElement(h);

				this._SkipSpace();
				int ch = this._getChar();
				if (ch == ',') {
					// has more
					this._readAndCheck(",");
				} else if (ch == '}') {
					// end of dict
					break;
				} else {
					// exception
					throw new JSONException("bad dict");
				}
			}

			this._readAndCheck("}");
			h.onDictEnd();

		}

		private void doReadString(final SajHandler h) throws IOException {

			String str = this._readString();
			h.onString(str);
			return;
		}

		private String _readString() throws IOException {

			this._readAndCheck("\"");
			StringBuffer sbuf = new StringBuffer();
			for (;;) {
				final int ch = this._read();
				if (ch == '"') {
					// end of string
					return sbuf.toString();
				} else if (ch < 0) {
					// EOF
					throw new JSONException("EOF in a string.");
				} else {
					if (ch == '\\') {
						System.err
								.println(this
										+ "[warning]:\n    current implement unsupport escaped string!!!\n");
					}
					sbuf.append((char) ch);
				}
			}

		}

		private int _read() throws IOException {
			final int b0 = this.mCurChar;
			this.mCurChar = this.mReader2.read();
			return b0;
		}

		private void doReadNumber(final SajHandler h) throws IOException {
			int ie = -1;
			int idot = -1;
			final StringBuffer sbuf = new StringBuffer();
			for (int i = 0;; i++) {
				int ch = this._getChar();
				if (ch == '.') {
					idot = i;
				} else if (ch == '-') {
				} else if (ch == '+') {
				} else if (ch == 'E') {
					ie = i;
				} else if (ch == 'e') {
					ie = i;
				} else if (('0' <= ch) && (ch <= '9')) {
				} else {
					break;
				}
				sbuf.append((char) ch);
				this._read();
			}
			final String s = sbuf.toString();
			if (ie < 0) {
				// one part
				if (idot < 0) {
					// int
					int n = Integer.parseInt(s);
					h.onInteger(n);
				} else {
					// float
					double n = Double.parseDouble(s);
					h.onDouble(n);
				}
			} else {
				// tow parts
				final String part1 = s.substring(0, ie);
				final String part2 = s.substring(ie + 1);
				final double np1 = Double.parseDouble(part1);
				final int np2 = Integer.parseInt(part2);
				throw new JSONException("unsupported float number:"
						+ (np1 + "e" + np2));
				// h.onDouble(n);
			}
		}

		private void doReadArray(final SajHandler h) throws IOException {

			this._readAndCheck("[");
			h.onArrayBegin();

			for (int i = 0;; i++) {

				if (i == 0) {
					this._SkipSpace();
					int ch = this._getChar();
					if (ch == ']') {
						break;
					}
				}

				this.doSkipTestReadElement(h);
				this._SkipSpace();
				int ch = this._getChar();
				if (ch == ',') {
					// has more
					this._readAndCheck(",");
				} else if (ch == ']') {
					// end of array
					break;
				} else {
					// exception
					throw new JSONException("bad array");
				}
			}

			this._readAndCheck("]");
			h.onArrayEnd();
		}

		private int _TestElementType() throws IOException {

			final int ch = this._getChar();

			switch (ch) {
			case '{':
				return TYPE_DICT;
			case '[':
				return TYPE_ARRAY;
			case '"':
				return TYPE_STRING;
			case 'n':
				return TYPE_NULL;
			case 't':
			case 'f':
				return TYPE_BOOLEAN;
			case '-':
				return TYPE_NUMBER;
			default:
				if (('0' <= ch) && (ch <= '9')) {
					return TYPE_NUMBER;
				} else {
					return -1;
				}
			}
		}

		private void _SkipSpace() throws IOException {
			for (;;) {
				final int ch = this._getChar();
				if (this._isSpaceChar(ch)) {
					// skip
					this._read();
				} else {
					return;
				}
			}
		}

		private boolean _isSpaceChar(int ch) {
			switch (ch) {
			case ' ':
			case 0x09:
			case 0x0a:
			case 0x0d:
				return true;
			default:
				return false;
			}
		}
	}

	static class MyLocatorReaderWrapper extends Reader implements
			SajLocator_internal {

		private final Reader mTarget;
		private int mLine;
		private int mColumn;
		private int mCount0x0a;
		private int mCount0x0d;
		private int mCountNL;

		public MyLocatorReaderWrapper(Reader target) {
			this.mTarget = target;
			this.mLine = 0;
			this.mColumn = 0;
		}

		@Override
		public int getLineIndex() {
			return this.mLine;
		}

		@Override
		public int getColumnIndex() {
			return this.mColumn;
		}

		@Override
		public void setColumnIndex(int index) {
			this.mColumn = index;
		}

		@Override
		public void setLineIndex(int index) {
			this.mLine = index;
		}

		@Override
		public void incColumnIndex() {
			this.mColumn++;
		}

		@Override
		public void newLine() {
			this.mColumn = 0;
			this.mLine++;
		}

		@Override
		public void close() throws IOException {
			this.mTarget.close();
		}

		@Override
		public int read(char[] cbuf, int off, int len) throws IOException {

			final int cb = this.mTarget.read(cbuf, off, len);
			final int end = off + cb;
			for (int i = off; i < end; i++) {
				final char ch = cbuf[i];
				this.incColumnIndex();

				if (ch == 0x0a || ch == 0x0d) {
					if (ch == 0x0d) {
						this.mCount0x0d++;
					}
					if (ch == 0x0a) {
						this.mCount0x0a++;
					}
					final int bigone = (this.mCount0x0a > this.mCount0x0d) ? this.mCount0x0a
							: this.mCount0x0d;
					if (bigone != this.mCountNL) {
						this.mCountNL = bigone;
						this.newLine();
					}

				}
			}

			return cb;
		}

	}

}
