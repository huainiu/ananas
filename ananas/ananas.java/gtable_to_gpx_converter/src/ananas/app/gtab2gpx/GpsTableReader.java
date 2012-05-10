package ananas.app.gtab2gpx;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class GpsTableReader implements PointReader {

	private final File mFile;
	private boolean mReadHeadOK = false;

	private FileInputStream mFIS;

	public GpsTableReader(File file) {
		this.mFile = file;
	}

	@Override
	public GeoPoint read() throws IOException {
		if (!this.mReadHeadOK) {
			this._readHead();
		}
		return this._readItem();
	}

	private GeoPoint _readItem() throws IOException {
		final int minColNum = 4;
		Vector<String> vline = this._readLine();
		while ((!this.mIsEOF) && (vline.size() < minColNum)) {
			vline = this._readLine();
		}
		if (vline.size() < minColNum) {
			return null;
		}
		GeoPoint pt = new GeoPoint();
		pt.longitude = this.mCpLon.getDoubleValue(vline);
		pt.latitude = this.mCpLat.getDoubleValue(vline);
		pt.altitude = this.mCpAlt.getDoubleValue(vline);
		pt.timestamp = this.mCpTime.getLongValueOfTimestamp(vline);
		{
			// anti warning
			this.mCpSrc.hashCode();
			this.mCpAccuracy.hashCode();
		}
		return pt;
	}

	private MyColumnPicker mCpLon;
	private MyColumnPicker mCpLat;
	private MyColumnPicker mCpAlt;
	private MyColumnPicker mCpSrc;
	private MyColumnPicker mCpTime;
	private MyColumnPicker mCpAccuracy;
	private Vector<String> mLineBuffer;
	private boolean mIsEOF = false;

	private void _readHead() throws IOException {

		this.mReadHeadOK = true;
		System.out.println("read from file " + this.mFile.getAbsolutePath());
		FileInputStream fis = new FileInputStream(this.mFile);

		this.mFIS = fis;
		Vector<String> v = this._readLine();

		int i = 0;
		for (String col : v) {

			// "timestamp" "longitude" "latitude" "altitude" "source" "accuracy"

			if ("timestamp".equalsIgnoreCase(col)) {
				this.mCpTime = new MyColumnPicker(i);

			} else if ("longitude".equalsIgnoreCase(col)) {
				this.mCpLon = new MyColumnPicker(i);

			} else if ("latitude".equalsIgnoreCase(col)) {
				this.mCpLat = new MyColumnPicker(i);

			} else if ("altitude".equalsIgnoreCase(col)) {
				this.mCpAlt = new MyColumnPicker(i);

			} else if ("source".equalsIgnoreCase(col)) {
				this.mCpSrc = new MyColumnPicker(i);

			} else if ("accuracy".equalsIgnoreCase(col)) {
				this.mCpAccuracy = new MyColumnPicker(i);
			}

			i++;
		}

	}

	private Vector<String> _readLine() throws IOException {
		Vector<String> v = this.mLineBuffer;
		if (v == null) {
			v = new Vector<String>();
			this.mLineBuffer = v;
		} else {
			v.removeAllElements();
		}
		InputStream is = this.mFIS;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int b = is.read(); !this.mIsEOF; b = is.read()) {
			boolean lineEnd = false;
			boolean colEnd = false;
			if (b < 0) {
				this.mIsEOF = true;
				colEnd = true;
				lineEnd = true;
			} else if (b == 0x09) {
				colEnd = true;
			} else if (b == 0x0a || b == 0x0d) {
				colEnd = true;
				lineEnd = true;
			} else {
				baos.write(b);
			}
			if (colEnd) {
				byte[] ba = baos.toByteArray();
				baos.reset();
				String str = new String(ba, "utf-8");
				if ((str.length() > 2) && str.startsWith("\"")
						&& str.endsWith("\"")) {
					str = str.substring(1, str.length() - 1);
				}
				v.addElement(str);
			}
			if (lineEnd) {
				break;
			}
		}
		return v;
	}

	@Override
	public void close() throws IOException {
		this.mFIS.close();
	}

	class MyColumnPicker {

		private final int mIndex;

		public MyColumnPicker(int i) {
			this.mIndex = i;
		}

		public long getLongValueOfTimestamp(Vector<String> vline) {
			String str = vline.get(mIndex);
			HttpTimeStamp time = HttpTimeStamp.parse(str);
			return time.toLong();
		}

		public double getDoubleValue(Vector<String> vline) {
			String str = vline.get(mIndex);
			return Double.parseDouble(str);
		}
	}

}
