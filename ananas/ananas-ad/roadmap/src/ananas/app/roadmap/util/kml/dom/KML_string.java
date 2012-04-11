package ananas.app.roadmap.util.kml.dom;

import ananas.app.roadmap.util.kml.KML_obj_base;

public class KML_string extends KML_obj_base {

	public String mData;

	@Override
	public boolean appendText(String text) {
		this.mData = text;
		return true;
	}

}
