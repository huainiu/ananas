package ananas.app.gtab2gpx;

public class GeoPoint {

	public GeoPoint(int i) {

		this.longitude = 110 + (i * 0.01);
		this.latitude = 25 + (i * 0.01);
		this.altitude = 300 + (i * 1);
		this.timestamp = ((2012 - 1970) * 365 * 24 * 3600L * 1000L)
				+ (i * 1000);

	}

	public GeoPoint() {
	}

	public double longitude;
	public double latitude;
	public double altitude;
	public long timestamp;
	public String source;

}
