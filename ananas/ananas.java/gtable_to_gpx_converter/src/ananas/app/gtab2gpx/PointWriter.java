package ananas.app.gtab2gpx;

import java.io.IOException;

public interface PointWriter {

	void write(GeoPoint pt) throws IOException;

	void close() throws IOException;

}
