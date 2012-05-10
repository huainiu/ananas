package ananas.app.gtab2gpx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class GpxFileWriter implements PointWriter {

	private String mName;
	private String mDescription;
	private final File mFile;
	private boolean mIsClosed;
	private boolean mIsOpen;
	private FileOutputStream mFOS;
	private MyPrintStream mPS;

	public GpxFileWriter(File file) {
		this.mFile = file;
	}

	@Override
	public void write(GeoPoint pt) throws IOException {
		this._openIfNeeded();
		if (pt == null)
			return;
		String strTime;
		{
			Calendar cale = Calendar.getInstance();
			cale.setTimeInMillis(pt.timestamp);
			int yy = cale.get(Calendar.YEAR);
			int mm = cale.get(Calendar.MONTH) + 1;
			int dd = cale.get(Calendar.DAY_OF_MONTH);
			int h = cale.get(Calendar.HOUR_OF_DAY);
			int m = cale.get(Calendar.MINUTE);
			int s = cale.get(Calendar.SECOND);
			strTime = _i2s(yy, 4) + "-" + _i2s(mm, 2) + "-" + _i2s(dd, 2) + "T"
					+ _i2s(h, 2) + ":" + _i2s(m, 2) + ":" + _i2s(s, 2) + "Z";
		}
		this.mPS.println("<trkpt lat=\"" + pt.latitude + "\" lon=\""
				+ pt.longitude + "\">");
		this.mPS.println("<ele>" + pt.altitude + "</ele>");
		this.mPS.println("<time>" + strTime + "</time>");
		this.mPS.println("</trkpt>");

	}

	private static String _i2s(int n, int width) {
		String str = "" + n;
		while (str.length() < width) {
			str = "0" + str;
		}
		return str;
	}

	private void _openIfNeeded() throws IOException {
		if (!this.mIsOpen) {
			this._doOpen();
		}
	}

	private void _doOpen() throws IOException {
		this.mIsOpen = true;
		FileOutputStream fos = new FileOutputStream(this.mFile);
		{
			System.out.println("write to file " + this.mFile.getAbsolutePath());
		}
		MyPrintStream ps = new MyPrintStream(fos);
		this.mPS = ps;
		this.mFOS = fos;
		if (this.mName == null) {
			this.mName = "no name";
		}
		ps.println("﻿<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
		ps.println("﻿<gpx xmlns=\"http://www.topografix.com/GPX/1/1\"  version=\"1.1\">");
		ps.println("﻿<trk>");
		ps.println("<name>﻿" + this.mName + "</name>");
		if (this.mDescription != null) {
			ps.println("﻿<desc>" + this.mDescription + "</desc>");
		}
		ps.println("﻿<trkseg>");
	}

	@Override
	public void close() throws IOException {
		if (this.mIsOpen && (!this.mIsClosed)) {
			this._doClose();
		}
	}

	private void _doClose() throws IOException {
		this.mIsClosed = true;
		this.mPS.println("</trkseg>");
		this.mPS.println("</trk>");
		this.mPS.println("</gpx>");

		this.mFOS.flush();
		this.mFOS.close();
	}

	public void setName(String string) {
		this.mName = string;
	}

	public void setDescription(String string) {
		this.mDescription = string;
	}

	private class MyPrintStream {

		private final OutputStream mOS;

		public MyPrintStream(OutputStream os) {
			this.mOS = os;
		}

		public void println(String string) throws IOException {
			byte[] ba = string.getBytes("utf-8");
			int off = 0;
			if (ba.length >= 3) {
				if ((ba[0] & 0x00ff) == 0xef) {
					off = 3;
				}
			}
			this.mOS.write(ba, off, ba.length - off);
			this.mOS.write(0x0d);
			this.mOS.write(0x0a);
		}
	}

}
