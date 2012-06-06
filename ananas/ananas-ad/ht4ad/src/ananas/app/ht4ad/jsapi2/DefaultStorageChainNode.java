package ananas.app.ht4ad.jsapi2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultStorageChainNode implements IStorageChainNode {

	private final Map<String, String> mMap;
	private IKeyValueMapper mNext;
	private int mRevisionRAM = 0;
	private int mRevisionROM = 0;

	public DefaultStorageChainNode() {
		this.mMap = new HashMap<String, String>();
	}

	public String get(String key) {
		if (key == null) {
			return null;
		}
		String value = this.mMap.get(key);
		if (value == null) {
			IKeyValueMapper next = this.mNext;
			if (next != null) {
				value = next.get(key);
			}
		}
		return value;
	}

	public void set(String key, String value) {
		if (key != null) {
			if (key.startsWith("storage.")) {
				if (value == null) {
					this.mMap.remove(key);
				} else {
					this.mMap.put(key, value);
				}
				this.mRevisionRAM++;
			} else {
				IKeyValueMapper next = this.mNext;
				if (next != null) {
					next.set(key, value);
				}
			}
		}
	}

	public IKeyValueMapper getNext() {
		return this.mNext;
	}

	public void setNext(IKeyValueMapper next) {
		this.mNext = next;
	}

	public void load(File file) {
		this.mMap.clear();
		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int b = is.read();; b = is.read()) {
				if (b == 0x0a || b == 0x0d || (b < 0)) {
					byte[] ba = baos.toByteArray();
					baos.reset();
					String str = new String(ba, "utf-8");
					int ieq = str.indexOf('=');
					if (ieq > 0) {
						String key = str.substring(0, ieq).trim();
						String value = str.substring(ieq + 1).trim();
						this.mMap.put(key, value);
					}
					if (b < 0)
						break;
				} else {
					baos.write(b);
				}
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.mRevisionRAM = this.mRevisionROM;
	}

	public void save(File file) {
		if (this.mRevisionRAM != this.mRevisionROM) {
			this.mRevisionROM = this.mRevisionRAM;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			Set<String> keyset = this.mMap.keySet();
			for (String key : keyset) {
				String value = this.mMap.get(key);
				ps.println(key + "=" + value);
			}
			ps.flush();
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(baos.toByteArray());
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String[] listKeys() {
		Set<String> keyset = this.mMap.keySet();
		IKeyValueMapper next = this.mNext;
		if (next != null) {
			String[] keys = next.listKeys();
			for (String key : keys) {
				keyset.add(key);
			}
		}
		return keyset.toArray(new String[keyset.size()]);
	}

}
