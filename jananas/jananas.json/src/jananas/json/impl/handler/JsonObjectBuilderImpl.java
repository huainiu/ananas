package jananas.json.impl.handler;

import jananas.json.JSON;
import jananas.json.JSONException;
import jananas.json.JSONObjectFactory;
import jananas.json.object.JSON_array;
import jananas.json.object.JSON_dictionary;
import jananas.json.object.JSON_number;
import jananas.json.object.JSON_object;
import jananas.json.object.JSON_string;

import java.util.Stack;

class JsonObjectBuilderImpl extends JsonObjectBuilder {

	private final Stack<JSON_object> mStack;
	private final JSONObjectFactory mObjFactory;
	private JSON_object mRootObj;
	private SajLocator mLocator;
	private String mCurDictKey;

	public JsonObjectBuilderImpl() {
		this.mStack = new Stack<JSON_object>();
		this.mObjFactory = JSON.getInstance().getJSONObjectFactory();
	}

	@Override
	public JSON_object getProduct() {
		return this.mRootObj;
	}

	@Override
	public void onDocBegin() {
		this.mStack.clear();
	}

	@Override
	public void onDocEnd() {
		// System.out.println("root JSON object:" + this.mRootObj);
		// this.mRootObj = null;
		this.mStack.clear();
	}

	@Override
	public void onException(JSONException e) {
		int line = this.mLocator.getLineIndex() + 1;
		int col = this.mLocator.getColumnIndex() + 1;
		System.err.println("JSON error, line:" + line + " column:" + col);
		e.printStackTrace();
	}

	@Override
	public void setLocator(SajLocator locator) {
		this.mLocator = locator;
	}

	@Override
	public void onNull() {
		JSON_object obj = this.mObjFactory.nullValue();
		this.push(obj);
		this.pop();
	}

	private void push(JSON_object obj) {

		final JSON_object child, parent;
		child = obj;
		if (this.mStack.empty()) {
			this.mRootObj = obj;
			parent = null;
		} else {
			parent = this.mStack.peek();
		}
		this.mStack.push(child);

		if (parent instanceof JSON_dictionary) {
			final JSON_dictionary pDict = (JSON_dictionary) parent;
			String key = this.mCurDictKey;
			this.mCurDictKey = null;
			pDict.put(key, child);

		} else if (parent instanceof JSON_array) {
			final JSON_array pArray = (JSON_array) parent;
			pArray.add(child);

		} else if (parent == null) {
			return;

		} else {
			throw new JSONException("bad type,\n    parent:" + parent
					+ "\n    child:" + child);
		}
	}

	private void pop() {
		this.mStack.pop();
	}

	@Override
	public void onBoolean(boolean b) {
		JSON_number obj = this.mObjFactory.booleanValue(b);
		this.push(obj);
		this.pop();
	}

	@Override
	public void onString(String s) {
		JSON_string obj = this.mObjFactory.string(s);
		this.push(obj);
		this.pop();
	}

	@Override
	public void onInteger(int n) {
		JSON_number obj = this.mObjFactory.intValue(n);
		this.push(obj);
		this.pop();
	}

	@Override
	public void onDouble(double n) {
		JSON_number obj = this.mObjFactory.doubleValue(n);
		this.push(obj);
		this.pop();
	}

	@Override
	public void onArrayBegin() {
		JSON_array obj = this.mObjFactory.array();
		this.push(obj);
	}

	@Override
	public void onArrayEnd() {
		this.pop();
	}

	@Override
	public void onDictBegin() {
		JSON_dictionary obj = this.mObjFactory.dictionary();
		this.push(obj);
	}

	@Override
	public void onDictKey(String key) {
		this.mCurDictKey = key;
	}

	@Override
	public void onDictEnd() {
		this.pop();
	}

}
