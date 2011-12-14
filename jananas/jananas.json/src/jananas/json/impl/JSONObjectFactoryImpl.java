package jananas.json.impl;

import jananas.json.JSONObjectFactory;
import jananas.json.object.JSONObjectCast;
import jananas.json.object.JSON_array;
import jananas.json.object.JSON_dictionary;
import jananas.json.object.JSON_null;
import jananas.json.object.JSON_number;
import jananas.json.object.JSON_object;
import jananas.json.object.JSON_string;

import java.util.Hashtable;
import java.util.Vector;



class JSONObjectFactoryImpl implements JSONObjectFactory {

	static class MyJSONObjectCast implements JSONObjectCast {

		protected JSON_null mNull;
		protected JSON_number mBoolean;

		@Override
		public JSON_array toArray() {
			return null;
		}

		@Override
		public JSON_dictionary toDictionary() {
			return null;
		}

		@Override
		public JSON_number toNumber() {
			return this.mBoolean;
		}

		@Override
		public JSON_string toJSONString() {
			return null;
		}

		@Override
		public JSON_null toNull() {
			return this.mNull;
		}
	}

	private static final JSON_null sJsonNull = new JSON_null() {

		private JSONObjectCast mCast;

		public String toString() {
			return "null";
		}

		@Override
		public JSONObjectCast cast() {
			JSONObjectCast cast = this.mCast;
			if (cast == null) {
				MyJSONObjectCast new_cast = new MyJSONObjectCast();
				new_cast.mNull = this;
				cast = new_cast;
				this.mCast = new_cast;
			}
			return cast;
		}

	};

	private static final JSON_number sJsonBooleanTrue = new JSON_number() {

		private JSONObjectCast mCast;

		public String toString() {
			return "true";
		}

		@Override
		public JSONObjectCast cast() {
			JSONObjectCast cast = this.mCast;
			if (cast == null) {
				MyJSONObjectCast new_cast = new MyJSONObjectCast();
				new_cast.mBoolean = this;
				cast = new_cast;
				this.mCast = new_cast;
			}
			return cast;
		}

		@Override
		public int integerValue() {
			return 0;
		}

		@Override
		public float floatValue() {
			return 0;
		}

		@Override
		public double doubleValue() {
			return 0;
		}

		@Override
		public boolean booleanValue() {
			return true;
		}

	};

	private static final JSON_number sJsonBooleanFalse = new JSON_number() {

		private JSONObjectCast mCast;

		public String toString() {
			return "false";
		}

		@Override
		public JSONObjectCast cast() {
			JSONObjectCast cast = this.mCast;
			if (cast == null) {
				MyJSONObjectCast new_cast = new MyJSONObjectCast();
				new_cast.mBoolean = this;
				cast = new_cast;
				this.mCast = new_cast;
			}
			return cast;
		}

		@Override
		public int integerValue() {
			return 0;
		}

		@Override
		public float floatValue() {
			return 0;
		}

		@Override
		public double doubleValue() {
			return 0;
		}

		@Override
		public boolean booleanValue() {
			return false;
		}

	};

	static final class MyIntValue implements JSON_number, JSONObjectCast {

		private final int mValue;

		public MyIntValue(int n) {
			this.mValue = n;
		}

		public String toString() {
			return ("" + this.mValue);
		}

		@Override
		public JSON_array toArray() {
			return null;
		}

		@Override
		public JSON_dictionary toDictionary() {
			return null;
		}

		@Override
		public JSON_number toNumber() {
			return this;
		}

		@Override
		public JSON_string toJSONString() {
			return null;
		}

		@Override
		public JSON_null toNull() {
			return null;
		}

		@Override
		public int integerValue() {
			return this.mValue;
		}

		@Override
		public float floatValue() {
			return this.mValue;
		}

		@Override
		public double doubleValue() {
			return this.mValue;
		}

		@Override
		public boolean booleanValue() {
			return (this.mValue > 0);
		}

		@Override
		public JSONObjectCast cast() {
			return this;
		}
	}

	static final class MyDoubleValue implements JSON_number, JSONObjectCast {

		private final double mValue;

		public MyDoubleValue(double n) {
			this.mValue = n;
		}

		public String toString() {
			return ("" + this.mValue);
		}

		@Override
		public JSON_array toArray() {
			return null;
		}

		@Override
		public JSON_dictionary toDictionary() {
			return null;
		}

		@Override
		public JSON_number toNumber() {
			return this;
		}

		@Override
		public JSON_string toJSONString() {
			return null;
		}

		@Override
		public JSON_null toNull() {
			return null;
		}

		@Override
		public int integerValue() {
			return (int) this.mValue;
		}

		@Override
		public float floatValue() {
			return (float) this.mValue;
		}

		@Override
		public double doubleValue() {
			return this.mValue;
		}

		@Override
		public boolean booleanValue() {
			return (this.mValue > 0.5);
		}

		@Override
		public JSONObjectCast cast() {
			return this;
		}
	}

	static final class MyStringValue implements JSON_string, JSONObjectCast {

		private final String mValue;

		public MyStringValue(String s) {
			this.mValue = s;
		}

		public String toString() {
			return ('"' + this.mValue + '"');
		}

		@Override
		public JSON_array toArray() {
			return null;
		}

		@Override
		public JSON_dictionary toDictionary() {
			return null;
		}

		@Override
		public JSON_number toNumber() {
			return null;
		}

		@Override
		public JSON_string toJSONString() {
			return this;
		}

		@Override
		public JSON_null toNull() {
			return null;
		}

		@Override
		public JSONObjectCast cast() {
			return this;
		}
	}

	static class MyArray implements JSON_array, JSONObjectCast {

		final Vector<JSON_object> mArray;

		public MyArray() {
			mArray = new Vector<JSON_object>();
		}

		public String toString() {
			final StringBuffer sbuf = new StringBuffer();
			sbuf.append('[');
			final JSON_object[] items = this.items();
			final int len = items.length;
			for (int i = 0; i < len; i++) {
				if (i > 0) {
					sbuf.append(',');
				}
				JSON_object item = items[i];
				sbuf.append(item);
			}
			sbuf.append(']');
			return sbuf.toString();
		}

		@Override
		public JSON_array toArray() {
			return this;
		}

		@Override
		public JSON_dictionary toDictionary() {
			return null;
		}

		@Override
		public JSON_number toNumber() {
			return null;
		}

		@Override
		public JSON_string toJSONString() {
			return null;
		}

		@Override
		public JSON_null toNull() {
			return null;
		}

		@Override
		public void add(JSON_object item) {
			this.mArray.addElement(item);
		}

		@Override
		public JSON_object get(int index) {
			return this.mArray.elementAt(index);
		}

		@Override
		public JSON_object delete(int index) {
			return this.mArray.remove(index);
		}

		@Override
		public JSON_object[] items() {
			JSON_object[] array = new JSON_object[this.mArray.size()];
			JSON_object[] array2 = this.mArray.toArray(array);
			return array2;
		}

		@Override
		public int count() {
			return this.mArray.size();
		}

		@Override
		public JSONObjectCast cast() {
			return this;
		}
	}

	static class MyDict implements JSON_dictionary, JSONObjectCast {

		final Hashtable<String, JSON_object> mDict;

		public MyDict() {
			this.mDict = new Hashtable<String, JSON_object>();
		}

		public String toString() {
			final StringBuffer sbuf = new StringBuffer();
			sbuf.append('{');
			String[] keys = this.keys();
			for (int i = keys.length - 1; i >= 0; i--) {
				String key = keys[i];
				JSON_object obj = this.get(key);
				sbuf.append('"' + key + '"' + ':');
				sbuf.append(obj);
				if (i == 0) {
					// the last one
				} else {
					sbuf.append(',');
				}
			}
			sbuf.append('}');
			return sbuf.toString();
		}

		@Override
		public JSON_array toArray() {
			return null;
		}

		@Override
		public JSON_dictionary toDictionary() {
			return this;
		}

		@Override
		public JSON_number toNumber() {
			return null;
		}

		@Override
		public JSON_string toJSONString() {
			return null;
		}

		@Override
		public JSON_null toNull() {
			return null;
		}

		@Override
		public void put(String key, JSON_object value) {
			this.mDict.put(key, value);
		}

		@Override
		public JSON_object get(String key) {
			return this.mDict.get(key);
		}

		@Override
		public JSON_object delete(String key) {
			return this.mDict.remove(key);
		}

		@Override
		public String[] keys() {
			String[] array = new String[this.mDict.size()];
			String[] array2 = this.mDict.keySet().toArray(array);
			// System.err.println(array);
			// System.err.println(array2);
			return array2;
		}

		@Override
		public int count() {
			return this.mDict.size();
		}

		@Override
		public JSONObjectCast cast() {
			return this;
		}
	}

	@Override
	public JSON_number booleanValue(boolean b) {
		if (b) {
			return sJsonBooleanTrue;
		} else {
			return sJsonBooleanFalse;
		}
	}

	@Override
	public JSON_number intValue(int n) {
		return new MyIntValue(n);
	}

	@Override
	public JSON_number doubleValue(double n) {
		return new MyDoubleValue(n);
	}

	@Override
	public JSON_string string(String s) {
		return new MyStringValue(s);
	}

	@Override
	public JSON_array array() {
		return new MyArray();
	}

	@Override
	public JSON_dictionary dictionary() {
		return new MyDict();
	}

	@Override
	public JSON_null nullValue() {
		return sJsonNull;
	}

}
