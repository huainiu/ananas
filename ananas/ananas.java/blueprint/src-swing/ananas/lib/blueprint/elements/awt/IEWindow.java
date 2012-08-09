package ananas.lib.blueprint.elements.awt;

import java.awt.Window;

public interface IEWindow extends IEContainer {

	public static class Wrapper extends IEContainer.Wrapper implements IEWindow {

		public static final String attr_width = "width";
		public static final String attr_height = "height";

		private String mHeight = "50";
		private String mWidth = "50";

		@Override
		public boolean setAttribute(String name, String value) {

			if (name == null) {
				return false;

			} else if (name.equals(attr_height)) {
				this.mHeight = value;

			} else if (name.equals(attr_width)) {
				this.mWidth = value;

			} else {
				return super.setAttribute(name, value);
			}

			return true;
		}

		@Override
		public Object createTarget() {
			return new Window(null);
		}

		@Override
		public void tagEnd() {

			super.tagEnd();

			Window window = (Window) this.getTarget(true);
			IAttrParser ap = this.getAttrParser();

			if (this.mHeight != null || this.mWidth != null) {
				int w = ap.parsePixels(this.mWidth);
				int h = ap.parsePixels(this.mHeight);
				window.setSize(w, h);
			}
		}

	}

}
