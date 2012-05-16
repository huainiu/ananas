package ananas.lib.main;

import ananas.lib.text_save.TextSave;
import ananas.lib.text_save.TextSaveDir;
import ananas.lib.text_save.TextSaveFile;
import android.app.Activity;
import android.os.Bundle;

public class TestTextSaveActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		TextSave ts = TextSave.Agent.getInstance();
		TextSaveDir dir = ts.getTextSaveDir("ananas/lib/textsave");
		TextSaveFile file = dir.getTextSaveFile("sample.txt");
		file.saveText("hello, world!");
		String str = file.loadText();
		System.out.println(str);

	}
}
