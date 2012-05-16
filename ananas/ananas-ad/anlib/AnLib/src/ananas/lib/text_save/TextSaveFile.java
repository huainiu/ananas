package ananas.lib.text_save;

public interface TextSaveFile {

	String loadText();

	void saveText(String text);

	void setPassword(String password);

}
