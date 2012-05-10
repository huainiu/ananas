package ananas.app.gtab2gpx;

import java.util.Hashtable;

public class ArgList {

	private final Hashtable<String, String> mTable = new Hashtable<String, String>();

	public ArgList(String[] arg) {
		String key = null;
		String value = null;
		for (String str : arg) {
			if (str.startsWith("-")) {
				// parameter start
				str = str.substring(1);
				key = str;
				value = null;
			} else {
				// parameter data
				if (value == null) {
					value = str;
				} else {
					value += str;
				}
				if (key != null) {
					this.mTable.put(key, value);
				}
			}
		}
	}

	public String getParameter(String string) {
		return this.mTable.get(string);
	}

}
