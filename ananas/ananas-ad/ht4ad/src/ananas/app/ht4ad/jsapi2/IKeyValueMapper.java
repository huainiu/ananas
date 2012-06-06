package ananas.app.ht4ad.jsapi2;

public interface IKeyValueMapper {

	String get(String key);

	void set(String key, String value);

	String[] listKeys();

}
