package ananas.roadmap.service;

import android.location.Location;

public interface ILocationRecordOutputStream {

	void close();

	void write(Location location);
}
