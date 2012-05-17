package ananas.lib.text_save;

import android.content.Context;

public interface TextSave {

	TextSaveDir getTextSaveDir(String path);

	public static final class Agent {

		public static TextSave getInstance(Context context) {
			return new ImplThisPackage(context);
		}

	}

}
