package ananas.app.roadmap.util.kml;

public interface IKMLObject {

	IKMLObject getParent();

	boolean setParent(IKMLObject parent);

	boolean appendChild(IKMLObject child);

	boolean appendText(String text);

	boolean setAttribute(String name, String value);

}
