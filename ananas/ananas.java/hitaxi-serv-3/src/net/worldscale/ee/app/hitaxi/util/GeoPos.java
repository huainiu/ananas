package net.worldscale.ee.app.hitaxi.util;

public interface GeoPos {

	double getLongitude();

	double getLatitude();

	public static class Factory {

		public static GeoPos parse(String str) {
			String strLon, strLat;
			strLon = strLat = null;
			str += ",";
			int from, to;
			from = to = 0;
			for (int i = 0; i < 3; i++) {
				to = str.indexOf(',', from);
				if (to < 0) {
					break;
				}
				String s = str.substring(from, to);
				switch (i) {
				case 0:
					strLon = s;
					break;
				case 1:
					strLat = s;
					break;
				default:
				}
			}
			double lon = Double.parseDouble(strLon);
			double lat = Double.parseDouble(strLat);
			return new MyImpl(lon, lat);
		}
	}

	static class MyImpl implements GeoPos {

		private final double mLon;
		private final double mLat;

		public MyImpl(double lon, double lat) {
			this.mLon = lon;
			this.mLat = lat;
		}

		@Override
		public double getLongitude() {
			return this.mLon;
		}

		@Override
		public double getLatitude() {
			return this.mLat;
		}
	}

}
