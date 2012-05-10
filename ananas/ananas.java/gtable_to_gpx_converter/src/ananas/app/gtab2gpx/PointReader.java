package ananas.app.gtab2gpx;

import java.io.IOException;

public interface PointReader {

	GeoPoint read() throws IOException;

	void close() throws IOException;
}
