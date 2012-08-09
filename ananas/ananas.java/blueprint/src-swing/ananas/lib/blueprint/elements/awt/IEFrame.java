package ananas.lib.blueprint.elements.awt;

import java.awt.Frame;

public interface IEFrame extends IEWindow {

	public static class Wrapper extends IEWindow.Wrapper implements IEFrame {

		public static final String attr_title = "title";

		private String mTitle;

		@Override
		public boolean setAttribute(String name, String value) {

			if (name == null) {
				return false;
			} else if (name.equals(attr_title)) {
				this.mTitle = value;
			} else {
				return super.setAttribute(name, value);
			}
			return true;
		}

		@Override
		public Object createTarget() {
			return new Frame();
		}

		@Override
		public void tagEnd() {

			super.tagEnd();

			Frame frame = (Frame) this.getTarget(true);

			if (this.mTitle != null)
				frame.setTitle(this.mTitle);

		}

	}

}
