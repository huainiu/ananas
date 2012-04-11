package ananas.app.roadmap.util.task;

import java.util.Hashtable;

import org.xml.sax.helpers.DefaultHandler;

import ananas.app.roadmap.util.kml.KML_kml;

public interface KMLObjectTreeBuilder {

	DefaultHandler getHandler();

	KML_kml getKmlRoot();

	public static class Factory {

		public static KMLObjectTreeBuilder newInstance() {
			return new MyImpl();
		}

	}

	class MyImpl implements KMLObjectTreeBuilder {

		private KML_kml mRoot;

		private MyImpl() {
		}

		@Override
		public DefaultHandler getHandler() {
			return new DefaultHandler() {
			};
		}

		@Override
		public KML_kml getKmlRoot() {
			return this.mRoot;
		}
	}

	class MyClassMapper {

		private final Hashtable<String, Class<?>> mTable;

		private MyClassMapper() {
			this.mTable = new Hashtable<String, Class<?>>();
			this._registerAll();
		}

		private void _registerAll() {
			String uri = "";

			this._registerClass(uri, "", KML_kml.class);

		}

		private void _registerClass(String uri, String string, Class<?> class1) {

		}
	}

}
