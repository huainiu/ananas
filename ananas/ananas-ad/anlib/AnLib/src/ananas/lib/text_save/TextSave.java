package ananas.lib.text_save;

public interface TextSave {

	TextSaveDir getTextSaveDir(String path);

	public static final class Agent {

		public static TextSave getInstance() {
			return new ImplThisPackage();
		}

	}

}
