package ananas.lib.text_save;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class ImplThisPackage implements TextSave {

	@Override
	public TextSaveDir getTextSaveDir(String path) {
		File sdcard = android.os.Environment.getExternalStorageDirectory();
		File base = sdcard;
		File file = new File(base, path);
		return new MyDir(file);
	}

	class MyDir implements TextSaveDir {

		private final File mFile;

		MyDir(File file) {
			this.mFile = file;
		}

		@Override
		public TextSaveFile getTextSaveFile(String path) {
			File file = new File(this.mFile, path);
			return new MyFile(file);
		}
	}

	class MyFile implements TextSaveFile {

		private final File mFile;
		private String mPassword;
		private String mPasswordMd5;

		MyFile(File file) {
			this.mFile = file;
		}

		@Override
		public String loadText() {
			try {
				final FileInputStream fis = new FileInputStream(this.mFile);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				final byte[] buf = new byte[512];
				for (int cb = fis.read(buf); cb > 0; cb = fis.read(buf)) {
					baos.write(buf, 0, cb);
				}
				fis.close();
				byte[] ba = baos.toByteArray();
				ba = this.doDecrypt(ba);
				return new String(ba, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		private byte[] doDecrypt(byte[] ba) {
			return ba;
		}

		private byte[] doEncrypt(byte[] ba) {
			return ba;
		}

		@Override
		public void saveText(String text) {
			try {
				File file = this.mFile;
				file.getParentFile().mkdirs();
				file.createNewFile();
				//
				byte[] ba = text.getBytes("utf-8");
				ba = this.doEncrypt(ba);
				final FileOutputStream fos = new FileOutputStream(file);
				fos.write(ba);
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void setPassword(String password) {
			this.mPassword = password;
			this.mPasswordMd5 = MyMD5.calcMD5(password);
		}
	}

	static class MyMD5 {

		public static byte[] calcMD5(byte[] source) {
			try {
				java.security.MessageDigest md;
				md = java.security.MessageDigest.getInstance("MD5");
				md.update(source);
				byte tmp[] = md.digest();
				return tmp;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public static String calcMD5(String source) {
			try {
				char[] chTable = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'a', 'b', 'c', 'd', 'e', 'f', '.' };
				byte[] ba = source.getBytes("utf-8");
				ba = calcMD5(ba);
				StringBuffer sbuf = new StringBuffer();
				for (byte b : ba) {
					for (int bit = 1; bit >= 0; bit--) {
						final int index = (b >> (bit * 4)) & 0x0000f;
						final char ch = chTable[index];
						sbuf.append(ch);
					}
				}
				return sbuf.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
