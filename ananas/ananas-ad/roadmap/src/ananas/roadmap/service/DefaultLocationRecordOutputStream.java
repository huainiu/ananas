package ananas.roadmap.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.TimeZone;

import org.json.JSONObject;

import android.location.Location;

public class DefaultLocationRecordOutputStream implements
		ILocationRecordOutputStream {

	boolean mIsClosed = false;
	private FileOutputStream mOutputStream;

	@Override
	protected void finalize() throws Throwable {
		this.close();
		super.finalize();
	}

	@Override
	public void close() {
		try {
			if (!this.mIsClosed) {
				this.mIsClosed = true;
				OutputStream os = this._getOutput();
				os.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(Location location) {
		try {

			String strTime = HttpTimeStampConvertor.getInstance()
					.millisecondToString(location.getTime());

			final JSONObject jo = new JSONObject();

			jo.put("latitude", location.getLatitude());
			jo.put("longitude", location.getLongitude());
			jo.put("altitude", location.getAltitude());
			jo.put("source", location.getProvider());
			jo.put("accuracy", location.getAccuracy());
			jo.put("time", strTime);

			OutputStream os = this._getOutput();
			String str = jo.toString() + "\n";
			byte[] buf = str.getBytes();
			synchronized (this) {
				os.write(buf);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private OutputStream _getOutput() throws IOException {
		if (this.mOutputStream == null && (!this.mIsClosed)) {
			final String timestamp = this._getCurrentTimeString();
			final File SDFile = android.os.Environment
					.getExternalStorageDirectory();
			final String path = SDFile.getAbsolutePath()
					+ "/ananas/roadmap/record_from_" + timestamp + ".txt";
			File file = new File(path);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			this.mOutputStream = fos;
		}
		return this.mOutputStream;
	}

	private String _add02(int n) {
		String s = n + "";
		if (s.length() == 1) {
			s = '0' + s;
		}
		return s;
	}

	private String _getCurrentTimeString() {

		final Calendar cale = Calendar.getInstance(TimeZone.getTimeZone(""));
		cale.setTimeInMillis(System.currentTimeMillis());

		final int yy, mm, dd, h, m, s;
		dd = cale.get(Calendar.DAY_OF_MONTH);
		mm = cale.get(Calendar.MONTH);
		yy = cale.get(Calendar.YEAR);
		h = cale.get(Calendar.HOUR_OF_DAY);
		m = cale.get(Calendar.MINUTE);
		s = cale.get(Calendar.SECOND);

		return (yy + "" + _add02(mm + 1) + "" + _add02(dd) + "-" + _add02(h)
				+ "" + _add02(m) + "" + _add02(s));
	}

}
