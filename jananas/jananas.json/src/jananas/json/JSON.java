package jananas.json;

import jananas.json.impl.JSON_xp_ModuleFactory;
import jananas.json.object.JSON_array;
import jananas.json.object.JSON_dictionary;
import jananas.json.object.JSON_number;
import jananas.json.object.JSON_string;

public abstract class JSON implements JSON_interface {

	/**
	 * module elements
	 * */

	private static JSON sInst;

	public static JSON getInstance() {
		if (sInst == null) {
			sInst = JSON_xp_ModuleFactory.newJSON();
		}
		return sInst;
	}

	/**
	 * factory function
	 * */

	public static JSON_number booleanValue(boolean b) {
		return JSON.getInstance().getJSONObjectFactory().booleanValue(b);
	}

	public static JSON_number intValue(int n) {
		return JSON.getInstance().getJSONObjectFactory().intValue(n);
	}

	public static JSON_number doubleValue(double n) {
		return JSON.getInstance().getJSONObjectFactory().doubleValue(n);
	}

	public static JSON_string string(String s) {
		return JSON.getInstance().getJSONObjectFactory().string(s);
	}

	public static JSON_array array() {
		return JSON.getInstance().getJSONObjectFactory().array();
	}

	public static JSON_dictionary dictionary() {
		return JSON.getInstance().getJSONObjectFactory().dictionary();
	}

}
