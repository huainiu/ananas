package jananas.json;

public interface JSONErrorInfo {

	int lineNumber(); // 0 based

	int columnNumber(); // 0 based

	int charNumber(); // 0 based

	int code();

	String message();

	Exception exception();

}
