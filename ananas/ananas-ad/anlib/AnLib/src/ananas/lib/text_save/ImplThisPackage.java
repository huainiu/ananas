package ananas.lib.text_save;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.telephony.TelephonyManager;

class ImplThisPackage implements TextSave {

	private final Context mContext;
	private String mDeviceID;

	public ImplThisPackage(Context context) {
		this.mContext = context;
	}

	private String getDeviceID() {
		String id = this.mDeviceID;
		if (id == null) {
			TelephonyManager tm = (TelephonyManager) this.mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			String tel = tm.getLine1Number();
			String iccid = tm.getSimSerialNumber();
			String imsi = tm.getSubscriberId();
			this.mDeviceID = id = imei + "|" + tel + "|" + iccid + "|" + imsi;
		}
		return id;
	}

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

		private byte[] doDecrypt(byte[] in) {
			MyRandomStreamGenerator rsg = this.getRSGen();
			int len = in.length;
			byte[] out = new byte[len];
			for (int i = 0; i < len; i++) {
				byte b1 = in[i];
				byte b2 = rsg.nextByte();
				out[i] = (byte) (b1 ^ b2);
			}

			// remove check sum at begin
			byte[] sum = this.caleCheckSum(out);
			for (byte b : sum) {
				if (b != 0) {
					throw new RuntimeException("check sum not match.");
				}
			}
			byte[] out2 = new byte[out.length - sum.length];
			for (int i = out2.length - 1; i >= 0; i--) {
				out2[i] = out[sum.length + i];
			}
			out = out2;

			return out;
		}

		private byte[] doEncrypt(byte[] in) {

			// add check sum at begin
			byte[] sum = this.caleCheckSum(in);
			byte[] in2 = new byte[sum.length + in.length];
			for (int i = sum.length - 1; i >= 0; i--) {
				byte b = sum[i];
				in2[i] = b;
			}
			for (int i = in.length - 1; i >= 0; i--) {
				byte b = in[i];
				in2[sum.length + i] = b;
			}
			in = in2;

			//
			MyRandomStreamGenerator rsg = this.getRSGen();
			int len = in.length;
			byte[] out = new byte[len];
			for (int i = 0; i < len; i++) {
				byte b1 = in[i];
				byte b2 = rsg.nextByte();
				out[i] = (byte) (b1 ^ b2);
			}
			return out;
		}

		private byte[] caleCheckSum(byte[] in) {
			final byte[] ret = new byte[4];
			final int retlen = ret.length;
			final int inlen = in.length;
			for (int i = retlen - 1; i >= 0; i--)
				ret[i] = 0;
			for (int i = 0; i < inlen; i++) {
				final int iret = i % retlen;
				final int b = in[i] ^ ret[iret];
				ret[iret] = (byte) b;
			}
			return ret;
		}

		private MyRandomStreamGenerator getRSGen() {
			String str1 = ImplThisPackage.this.getDeviceID();
			String str2 = this.mFile.getAbsolutePath();
			String seed = MyMD5.calcMD5(str1 + str2);
			return new MyRandomStreamGenerator(seed);
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

	}

	static class MyRandomStreamGenerator {

		private byte[] mSeed;
		private int mCurPos = 0;
		private int mCurStep = 23;

		public MyRandomStreamGenerator(String seed) {
			this.mSeed = seed.getBytes();

			StringBuffer sbuf = new StringBuffer();
			sbuf.append(this + "|");
			sbuf.append(seed + "|");
			for (int i = 32; i > 0; i--) {
				sbuf.append(this.nextByte());
				sbuf.append(',');
			}
			// System.out.println(sbuf);

		}

		public byte nextByte() {
			byte ret = 0;
			for (int i = 8; i > 0; i--) {
				ret <<= 1;
				ret |= (this.nextBit() ? 1 : 0);
			}
			this.mCurStep = ret & 0x00ff;
			return ret;
		}

		private boolean nextBit() {
			int step = (this.mCurStep > 1) ? this.mCurStep : 1;
			int pos = (this.mCurPos += step);
			int iBit = pos % 8;
			int iByte = (pos / 8) % this.mSeed.length;
			int b = (this.mSeed[iByte] >> iBit) & 0x01;
			return (b != 0);
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
