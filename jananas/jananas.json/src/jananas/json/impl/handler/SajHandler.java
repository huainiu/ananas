package jananas.json.impl.handler;

import jananas.json.JSONException;

public interface SajHandler {

	/**
	 * control
	 * */

	void onDocBegin();

	void onDocEnd();

	void onException(JSONException e);

	void setLocator(SajLocator locator);

	/**
	 * elements
	 * */

	void onNull();

	void onBoolean(boolean b);

	void onString(String s);

	void onInteger(int n);

	void onDouble(double n);

	void onArrayBegin();

	void onArrayEnd();

	void onDictBegin();

	void onDictKey(String key);

	void onDictEnd();

}
