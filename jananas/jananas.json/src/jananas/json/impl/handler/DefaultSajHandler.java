package jananas.json.impl.handler;

import jananas.json.JSONException;

public class DefaultSajHandler implements SajHandler {

	public static SajHandler getTestHandler_toString() {
		return new SajHandler() {

			private SajLocator mLocator;

			@Override
			public void onDocBegin() {
				System.out.println(this + ".onDocBegin()");
			}

			@Override
			public void onDocEnd() {
				System.out.println(this + ".onDocEnd()");
			}

			@Override
			public void onException(JSONException e) {

				int line = this.mLocator.getLineIndex();
				int col = this.mLocator.getColumnIndex();

				System.out.println(this + ".onException()");
				System.out.println("line:" + line + " column:" + col);
				e.printStackTrace(System.out);
			}

			@Override
			public void setLocator(SajLocator locator) {
				System.out.println(this + ".setLocator()");
				this.mLocator = locator;
			}

			@Override
			public void onNull() {
				System.out.println("null");
			}

			@Override
			public void onBoolean(boolean b) {
				System.out.println(b);
			}

			@Override
			public void onString(String s) {
				System.out.println('"' + s + '"');
			}

			@Override
			public void onInteger(int n) {
				System.out.println(n);
			}

			@Override
			public void onDouble(double n) {
				System.out.println(n);
			}

			@Override
			public void onArrayBegin() {
				System.out.println("[");
			}

			@Override
			public void onArrayEnd() {
				System.out.println("]");
			}

			@Override
			public void onDictBegin() {
				System.out.println("{");
			}

			@Override
			public void onDictKey(String key) {
				System.out.println('"' + key + "\":");
			}

			@Override
			public void onDictEnd() {
				System.out.println("}");
			}
		};
	}

	@Override
	public void onDocBegin() {
		System.out.println(this + ".onDocBegin");
	}

	@Override
	public void onDocEnd() {
		System.out.println(this + ".onDocEnd");

	}

	@Override
	public void onException(JSONException e) {
		System.out.println(this + ".onException");
		e.printStackTrace();
	}

	@Override
	public void onNull() {
		System.out.println(this + ".onNull");

	}

	@Override
	public void onBoolean(boolean b) {
		System.out.println(this + ".onBoolean:" + b);

	}

	@Override
	public void onString(String s) {
		System.out.println(this + ".onString:" + s);

	}

	@Override
	public void onInteger(int n) {
		System.out.println(this + ".onInt:" + n);

	}

	@Override
	public void onDouble(double n) {
		System.out.println(this + ".onDouble:" + n);

	}

	@Override
	public void onArrayBegin() {
		System.out.println(this + ".onArrayBegin");

	}

	@Override
	public void onArrayEnd() {
		System.out.println(this + ".onArrayEnd");

	}

	@Override
	public void onDictBegin() {
		System.out.println(this + ".onDictBegin");

	}

	@Override
	public void onDictKey(String key) {
		System.out.println(this + ".onDictKey:" + key);

	}

	@Override
	public void onDictEnd() {
		System.out.println(this + ".onDictEnd");

	}

	@Override
	public void setLocator(SajLocator locator) {
		// TODO Auto-generated method stub

	}

}
