package ananas.app.htcplink;

import java.util.Hashtable;

public interface CmdLineArgs {

	String getValue(String key);

	String[] keys();

	public class Factory {

		public static CmdLineArgs newInstance(String[] args) {
			MyImpl impl = new MyImpl();
			impl.parse(args);
			return impl;
		}

		private static class MyImpl implements CmdLineArgs {

			private final Hashtable<String, String> mTable;

			public MyImpl() {
				this.mTable = new Hashtable<String, String>();
			}

			public void parse(String[] args) {
				String key = null;
				for (String str : args) {
					str = str.trim();
					if (str.startsWith("-")) {
						// key
						key = str.substring(1);
					} else {
						// value
						String value = str;
						if (key != null && value != null) {
							this.mTable.put(key, value);
						}
					}
				}
			}

			@Override
			public String getValue(String key) {
				return this.mTable.get(key);
			}

			@Override
			public String[] keys() {
				return this.mTable.keySet().toArray(
						new String[this.mTable.size()]);
			}
		}
	}

}
