package ananas.lib.blueprint.elements.awt;

import ananas.lib.blueprint.DefaultElement;
import ananas.lib.blueprint.IElement;

public interface IEObject extends IElement {

	public static class Wrapper extends DefaultElement implements IEObject {

		private static final IAttrParser sAttrParser;

		static {
			sAttrParser = new DefaultAttrParser();
		}

		public IAttrParser getAttrParser() {
			return sAttrParser;
		}

	}

	public static interface IAttrParser {

		int parsePixels(String s);
	}

	public static class DefaultAttrParser implements IAttrParser {

		@Override
		public int parsePixels(String s) {
			if (s == null)
				return 0;
			return Integer.parseInt(s);
		}
	}

}
