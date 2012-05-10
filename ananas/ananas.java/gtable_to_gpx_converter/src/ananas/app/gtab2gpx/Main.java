package ananas.app.gtab2gpx;

import java.io.File;

public class Main implements Runnable {

	private final ArgList mArgList;

	public Main(ArgList al) {
		this.mArgList = al;
	}

	public static void main(String[] arg) {
		String appname = "Gtable-to-Gpx-Converter";
		System.out.println(appname + " - begin");

		ArgList al = new ArgList(arg);
		Main proc = new Main(al);
		proc.run();

		System.out.println(appname + " - end");
	}

	@Override
	public void run() {
		try {
			String in = this.mArgList.getParameter("in");
			String out = this.mArgList.getParameter("out");

			File fileIn = new File(in);
			File fileOut = new File(out);

			GpsTableReader gtab = new GpsTableReader(fileIn);
			GpxFileWriter gpx = new GpxFileWriter(fileOut);
			gpx.setName(fileIn.getName());
			gpx.setDescription(fileIn.getName());
			for (GeoPoint pt = gtab.read(); pt != null; pt = gtab.read()) {
				gpx.write(pt);
			}
			gpx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
